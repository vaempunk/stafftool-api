package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.DepartmentDto;
import com.vaempunk.stafftool.entity.Department;

@Component
public class DepartmentMapper {

    public DepartmentDto toDto(Department e) {
        var dto = new DepartmentDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        return dto;
    }

    public void updateFromDto(Department department, DepartmentDto dto) {
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
    }

}
