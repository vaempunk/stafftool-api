package com.vaempunk.stafftool.util.mapper;

import org.springframework.stereotype.Component;

import com.vaempunk.stafftool.dto.EmployeeDto;
import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.entity.Gender;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(Employee e) {
        var dto = new EmployeeDto();
        dto.setId(e.getId());
        dto.setFirstname(e.getFirstname());
        dto.setLastname(e.getLastname());
        dto.setEmail(e.getEmail());
        dto.setPhoneNumber(e.getPhoneNumber());
        dto.setGender(e.getGender().toString());
        dto.setBirthDate(e.getBirthDate());
        dto.setCountry(e.getCountry());
        dto.setCity(e.getCity());
        dto.setAddress(e.getAddress());
        return dto;
    }

    public void updateFromDto(Employee employee, EmployeeDto dto) {
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setGender(Gender.from(dto.getGender()));
        employee.setBirthDate(dto.getBirthDate());
        employee.setCountry(dto.getCountry());
        employee.setCity(dto.getCity());
        employee.setAddress(dto.getAddress());
    }

}
