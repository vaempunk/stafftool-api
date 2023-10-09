package com.vaempunk.stafftool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.EmployeeDto;
import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.exception.ResourceNotFoundException;
import com.vaempunk.stafftool.repository.EmployeeRepository;
import com.vaempunk.stafftool.util.mapper.EmployeeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDto get(long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    public EmployeeDto add(EmployeeDto newEmployee) {
        var employee = new Employee();
        employeeMapper.updateFromDto(employee, newEmployee);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto update(EmployeeDto newEmployee) {
        var employee = employeeRepository.findById(newEmployee.getId())
                .orElseThrow(ResourceNotFoundException::new);
        employeeMapper.updateFromDto(employee, newEmployee);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public void delete(long id) {
        if (!employeeRepository.existsById(id))
            throw new ResourceNotFoundException();
        employeeRepository.deleteById(id);
    }

}
