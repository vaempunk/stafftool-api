package com.vaempunk.stafftool.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.PageDto;
import com.vaempunk.stafftool.dto.TeamDto;
import com.vaempunk.stafftool.entity.Team;
import com.vaempunk.stafftool.exception.ResourceConflictException;
import com.vaempunk.stafftool.exception.ResourceNotFoundException;
import com.vaempunk.stafftool.repository.DepartmentRepository;
import com.vaempunk.stafftool.repository.TeamRepository;
import com.vaempunk.stafftool.util.mapper.TeamMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final DepartmentRepository departmentRepository;
    private final TeamMapper teamMapper;

    public TeamDto get(long id) {
        return teamRepository.findById(id)
                .map(teamMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public PageDto<TeamDto> getAllByDepartmentId(long departmentId, Pageable pageable) {
        var teams = teamRepository.findAllByDepartmentId(departmentId, pageable)
                .map(teamMapper::toDto);
        return new PageDto<>(teams.getContent(), teams.getTotalPages(), teams.getNumber());
    }

    public PageDto<TeamDto> getAll(Pageable pageable) {
        var teams = teamRepository.findAll(pageable)
                .map(teamMapper::toDto);
        return new PageDto<>(teams.getContent(), teams.getTotalPages(), teams.getNumber());
    }

    public TeamDto add(TeamDto newTeam) {
        if (!isTeamNameAvailable(newTeam.getDepartmentId(), newTeam.getName()))
            throw new ResourceConflictException();
        var team = new Team();
        teamMapper.updateFromDto(team, newTeam);
        var department = departmentRepository.findById(newTeam.getDepartmentId())
                .orElseThrow(ResourceNotFoundException::new);
        team.setDepartment(department);
        teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    public TeamDto update(TeamDto newTeam) {
        var team = teamRepository.findById(newTeam.getId())
                .orElseThrow(ResourceNotFoundException::new);
        if (!(team.getName().equals(newTeam.getName()) && team.getDepartment().getId() == newTeam.getDepartmentId())
                || isTeamNameAvailable(newTeam.getDepartmentId(), newTeam.getName()))
            throw new ResourceConflictException();
        teamMapper.updateFromDto(team, newTeam);
        var department = departmentRepository.findById(newTeam.getDepartmentId())
                .orElseThrow(ResourceNotFoundException::new);
        team.setDepartment(department);
        teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    public void delete(long id) {
        if (!teamRepository.existsById(id))
            throw new ResourceNotFoundException();
        teamRepository.deleteById(id);
    }

    public boolean isTeamNameAvailable(long departmentId, String name) {
        return !teamRepository.existsByDepartmentIdAndNameIgnoreCase(departmentId, name);
    }

}
