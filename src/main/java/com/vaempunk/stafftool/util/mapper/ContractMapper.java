package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.ContractDTO;
import com.vaempunk.stafftool.entity.Contract;
import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.entity.Team;
import com.vaempunk.stafftool.exception.EmployeeException;
import com.vaempunk.stafftool.exception.TeamException;
import com.vaempunk.stafftool.service.EmployeeService;
import com.vaempunk.stafftool.service.TeamService;

@Component
public class ContractMapper {

    private final EmployeeService employeeService;
    private final TeamService teamService;

    public ContractMapper(EmployeeService employeeService, TeamService teamService) {
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    public ContractDTO toDTO(Contract e) {

        ContractDTO dto = new ContractDTO();
        dto.setId(e.getId());
        dto.setEmployeeId(e.getEmployee().getId());
        dto.setTeamId(e.getTeam().getId());
        dto.setJobName(e.getJobName());
        dto.setStartDate(e.getStartDate());
        dto.setEndDate(e.getEndDate());
        dto.setSalary(e.getSalary());
        return dto;
    }

    public Contract toEntity(ContractDTO dto) throws EmployeeException, TeamException {
        Employee employee = employeeService.get(dto.getEmployeeId());
        Team team = teamService.get(dto.getTeamId());

        Contract e = new Contract();
        e.setId(dto.getId());
        e.setEmployee(employee);
        e.setTeam(team);
        e.setJobName(dto.getJobName());
        e.setStartDate(dto.getStartDate());
        e.setEndDate(dto.getEndDate());
        e.setSalary(dto.getSalary());
        return e;
    }
    
}
