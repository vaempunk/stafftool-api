package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.ContractDto;
import com.vaempunk.stafftool.entity.Contract;

@Component
public class ContractMapper {

    public ContractDto toDto(Contract e) {
        var dto = new ContractDto();
        dto.setId(e.getId());
        dto.setEmployeeId(e.getEmployee().getId());
        dto.setTeamId(e.getTeam().getId());
        dto.setJobName(e.getJobName());
        dto.setStartDate(e.getStartDate());
        dto.setEndDate(e.getEndDate());
        dto.setSalary(e.getSalary());
        return dto;
    }

    public void updateFromDto(Contract contract, ContractDto dto) {
        contract.setJobName(dto.getJobName());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setSalary(dto.getSalary());
    }

}
