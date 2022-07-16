package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.DepartmentDTO;
import com.vaempunk.stafftool.entity.Department;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDTO(Department e) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        return dto;
    }

    public Department toEntity(DepartmentDTO dto) {
        Department e = new Department();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setDescription(dto.getDescription());
        return e;
    }
}
