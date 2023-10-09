package com.vaempunk.stafftool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.TeamDto;
import com.vaempunk.stafftool.entity.Team;
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

    public List<TeamDto> getAllByDepartmentId(long departmentId) {
        return teamRepository.findAllByDepartmentId(departmentId).stream()
                .map(teamMapper::toDto)
                .toList();
    }

    public List<TeamDto> getAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .toList();
    }

    public TeamDto add(TeamDto newTeam) {
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

}
