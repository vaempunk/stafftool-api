package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.TeamDto;
import com.vaempunk.stafftool.entity.Team;

@Component
public class TeamMapper {

    public TeamDto toDto(Team e) {
        var dto = new TeamDto();
        dto.setId(e.getId());
        dto.setDepartmentId(e.getDepartment().getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        return dto;
    }

    public void updateFromDto(Team team, TeamDto dto) {
        team.setName(dto.getName());
        team.setDescription(dto.getDescription());
    }

}
