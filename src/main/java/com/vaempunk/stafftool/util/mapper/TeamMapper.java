package com.vaempunk.stafftool.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.MappingConstants.ComponentModel;

import com.vaempunk.stafftool.dto.TeamDto;
import com.vaempunk.stafftool.entity.Team;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TeamMapper {
    
    @Mapping(target = "departmentId", source = "department.id")
    TeamDto toDto(Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    void updateFromDto(@MappingTarget Team team, TeamDto teamDto);

}
