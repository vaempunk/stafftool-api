package com.vaempunk.stafftool.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.MappingConstants.ComponentModel;

import com.vaempunk.stafftool.dto.DepartmentDto;
import com.vaempunk.stafftool.entity.Department;

@Mapper(componentModel = ComponentModel.SPRING)
public interface DepartmentMapper {
    
    DepartmentDto toDto(Department department);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(@MappingTarget Department department, DepartmentDto dto);

}
