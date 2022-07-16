package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.EmployeeDTO;
import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.entity.enumtype.GenderType;

@Component
public class EmployeeMapper {
    
    public EmployeeDTO toDTO(Employee e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setEmail(e.getEmail());
        dto.setPhoneNumber(e.getPhoneNumber());
        dto.setGender(e.getGender().toString());
        dto.setBirthDate(e.getBirthDate());
        dto.setCountry(e.getCountry());
        dto.setCity(e.getCity());
        dto.setAddress(e.getAddress());
        return dto;
    }

    public Employee toEntity(EmployeeDTO dto) {
        Employee e = new Employee();
        e.setId(dto.getId());
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setEmail(dto.getEmail());
        e.setPhoneNumber(dto.getPhoneNumber());
        e.setGender(GenderType.from(dto.getGender()));
        e.setBirthDate(dto.getBirthDate());
        e.setCountry(dto.getCountry());
        e.setCity(dto.getCity());
        e.setAddress(dto.getAddress());
        return e;
    }
}
