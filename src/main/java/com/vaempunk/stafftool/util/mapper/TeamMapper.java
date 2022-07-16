package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.TeamDTO;
import com.vaempunk.stafftool.entity.Department;
import com.vaempunk.stafftool.entity.Team;
import com.vaempunk.stafftool.exception.DepartmentException;
import com.vaempunk.stafftool.service.DepartmentService;

@Component
public class TeamMapper {

    private final DepartmentService departmentService;

    // @Autowired
    public TeamMapper(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public TeamDTO toDTO(Team e) {
        TeamDTO dto = new TeamDTO();
        dto.setId(e.getId());
        dto.setDepartmentId(e.getDepartment().getId());
        dto.setName(e.getName());
        dto.setDescription(e.getDescription());
        return dto;
    }

    public Team toEntity(TeamDTO dto) throws DepartmentException {
        Department department = departmentService.get(dto.getDepartmentId());

        Team e = new Team();
        e.setId(dto.getId());
        e.setDepartment(department);
        e.setName(dto.getName());
        e.setDescription(dto.getDescription());
        return e;
    }

}
