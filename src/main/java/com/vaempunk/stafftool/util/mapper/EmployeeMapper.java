package com.vaempunk.stafftool.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

import com.vaempunk.stafftool.dto.EmployeeDto;
import com.vaempunk.stafftool.entity.Employee;

@Mapper(componentModel = ComponentModel.SPRING)
public interface EmployeeMapper {
    
    @Mapping(target = "teamId", source = "team.id")
    EmployeeDto toDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    void updateFromDto(@MappingTarget Employee employee, EmployeeDto dto);

}
