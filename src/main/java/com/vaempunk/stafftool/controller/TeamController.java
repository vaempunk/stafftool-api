package com.vaempunk.stafftool.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vaempunk.stafftool.dto.TeamDTO;
import com.vaempunk.stafftool.entity.Team;
import com.vaempunk.stafftool.exception.DepartmentException;
import com.vaempunk.stafftool.exception.TeamException;
import com.vaempunk.stafftool.service.TeamService;
import com.vaempunk.stafftool.util.mapper.TeamMapper;

@RestController
@RequestMapping
public class TeamController {

    private final TeamService teamService;

    private final TeamMapper teamMapper;

    // @Autowired
    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/teams/{id}")
    public TeamDTO get(@PathVariable Integer id) {

        try {
            Team team = teamService.get(id);

            return teamMapper.toDTO(team);
        } catch (TeamException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }

    @GetMapping("/teams")
    public List<TeamDTO> getAll() {

        List<Team> teams = teamService.getAll();

        return teams
                .stream()
                .map(teamMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/departments/{departmentId}/teams")
    public List<TeamDTO> getAllByDepartmentName(@PathVariable Integer departmentId) {

        List<Team> teams = teamService.getAllByDepartmentId(departmentId);

        return teams
                .stream()
                .map(teamMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/teams")
    public TeamDTO add(@RequestBody TeamDTO teamDTO) {

        try {
            Team team = teamService.add(teamMapper.toEntity(teamDTO));

            return teamMapper.toDTO(team);
        } catch (TeamException exc) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, exc.toString());
        } catch (DepartmentException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.toString());
        }
    }

    @PutMapping("/teams/{id}")
    public TeamDTO update(@PathVariable Integer id, @RequestBody TeamDTO teamDTO) {

        try {
            Team team = teamService.update(id, teamMapper.toEntity(teamDTO));

            return teamMapper.toDTO(team);
        } catch (TeamException exc) {

            switch (exc.getType()) {
                case NOT_FOUND:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
                case ALREADY_EXISTS:
                    throw new ResponseStatusException(HttpStatus.CONFLICT, exc.toString());
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } catch (DepartmentException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.toString());
        }
    }

    @DeleteMapping("/teams/{id}")
    public TeamDTO delete(@PathVariable Integer id) {

        try {
            Team team = teamService.delete(id);

            return teamMapper.toDTO(team);
        } catch (TeamException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }
}
