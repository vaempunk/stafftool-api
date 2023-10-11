package com.vaempunk.stafftool.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.EmployeeDto;
import com.vaempunk.stafftool.dto.PageDto;
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

    public PageDto<EmployeeDto> getAll(Pageable pageable) {
        var employees = employeeRepository.findAll(pageable)
                .map(employeeMapper::toDto);
        return new PageDto<>(employees.getContent(), employees.getTotalPages(), employees.getNumber());
    }

    public EmployeeDto add(EmployeeDto newEmployee) {
        if (!isEmailAvailable(newEmployee.getEmail()))
            throw new ResourceNotFoundException();
        if (!isPhoneAvailable(newEmployee.getPhone()))
            throw new ResourceNotFoundException();
        var employee = new Employee();
        employeeMapper.updateFromDto(employee, newEmployee);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto update(EmployeeDto newEmployee) {
        var employee = employeeRepository.findById(newEmployee.getId())
                .orElseThrow(ResourceNotFoundException::new);
        if (!(employee.getEmail().equals(newEmployee.getEmail()) || isEmailAvailable(newEmployee.getEmail())))
            throw new ResourceNotFoundException();
        if (!(employee.getPhone().equals(newEmployee.getPhone()) || isPhoneAvailable(newEmployee.getPhone())))
            throw new ResourceNotFoundException();
        employeeMapper.updateFromDto(employee, newEmployee);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public void delete(long id) {
        if (!employeeRepository.existsById(id))
            throw new ResourceNotFoundException();
        employeeRepository.deleteById(id);
    }

    public boolean isEmailAvailable(String email) {
        return !employeeRepository.existsByEmail(email);
    }

    public boolean isPhoneAvailable(String phone) {
        return !employeeRepository.existsByPhone(phone);
    }

}
