package com.vaempunk.stafftool.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaempunk.stafftool.dto.TeamDto;
import com.vaempunk.stafftool.service.TeamService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TeamResource {

    private final TeamService teamService;

    @GetMapping("/teams/{id}")
    public TeamDto get(@PathVariable("id") Long id) {
        return teamService.get(id);
    }

    @GetMapping("/teams")
    public List<TeamDto> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/departments/{departmentId}/teams")
    public List<TeamDto> getAllByDepartmentName(@PathVariable("departmentId") Long departmentId) {
        return teamService.getAllByDepartmentId(departmentId);
    }

    @PostMapping("/teams")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TeamDto add(@RequestBody @Valid TeamDto teamDto) {
        return teamService.add(teamDto);
    }

    @PutMapping("/teams/{id}")
    public TeamDto update(@PathVariable("id") Long id, @RequestBody TeamDto teamDto) {
        teamDto.setId(id);
        return teamService.update(teamDto);
    }

    @DeleteMapping("/teams/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        teamService.delete(id);
    }

}
