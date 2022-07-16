package com.vaempunk.stafftool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.entity.Team;
import com.vaempunk.stafftool.exception.EntityExceptionType;
import com.vaempunk.stafftool.exception.TeamException;
import com.vaempunk.stafftool.repository.TeamRepository;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    // @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team get(Integer id)
            throws TeamException {

        Optional<Team> teamOpt = teamRepository.findById(id);
        if (teamOpt.isEmpty()) {
            throw new TeamException(EntityExceptionType.NOT_FOUND);
        }

        return teamOpt.get();
    }

    public List<Team> getAllByDepartmentId(Integer departmentId) {

        return teamRepository.findAllByDepartmentId(departmentId);
    }

    public List<Team> getAll() {

        List<Team> teams = teamRepository.findAll();

        return teams;
    }

    public Team add(Team newTeam)
            throws TeamException {

        if (teamRepository.existsByName(newTeam.getName())) {
            throw new TeamException(EntityExceptionType.ALREADY_EXISTS);
        }

        Team team = teamRepository.save(newTeam);

        return team;
    }

    public Team update(Integer id, Team newTeam)
            throws TeamException {

        if (teamRepository.existsByNameAndIdNot(newTeam.getName(), id)) {
            throw new TeamException(EntityExceptionType.ALREADY_EXISTS);
        }

        Optional<Team> teamOpt = teamRepository.findById(id);
        if (teamOpt.isEmpty()) {
            throw new TeamException(EntityExceptionType.NOT_FOUND);
        }

        Team team = teamOpt.get();
        team.setDepartment(newTeam.getDepartment());
        team.setName(newTeam.getName());
        team.setDescription(newTeam.getDescription());
        teamRepository.save(team);

        return team;
    }

    public Team delete(Integer id)
            throws TeamException {

        Optional<Team> teamOpt = teamRepository.findById(id);
        if (teamOpt.isEmpty()) {
            throw new TeamException(EntityExceptionType.NOT_FOUND);
        }

        Team team = teamOpt.get();
        teamRepository.delete(team);

        return team;
    }

}
