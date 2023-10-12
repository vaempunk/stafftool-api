package com.vaempunk.stafftool.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;

import com.vaempunk.stafftool.dto.ContractDto;
import com.vaempunk.stafftool.entity.Contract;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ContractMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "departmentId", source = "department.id")
    ContractDto toDto(Contract contract);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "department", ignore = true)
    void updateFromDto(@MappingTarget Contract contract, ContractDto dto);

}
