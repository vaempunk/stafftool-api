package com.vaempunk.stafftool.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.EmployeeDto;
import com.vaempunk.stafftool.dto.PageDto;
import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.exception.ResourceConflictException;
import com.vaempunk.stafftool.exception.ResourceNotFoundException;
import com.vaempunk.stafftool.repository.EmployeeRepository;
import com.vaempunk.stafftool.repository.TeamRepository;
import com.vaempunk.stafftool.util.mapper.EmployeeMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
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

    public PageDto<EmployeeDto> getAllByTeamId(long teamId, Pageable pageable) {
        var employees = employeeRepository.findAllByTeamId(teamId, pageable)
                .map(employeeMapper::toDto);
        return new PageDto<>(employees.getContent(), employees.getTotalPages(), employees.getNumber());
    }

    public EmployeeDto add(EmployeeDto newEmployee) {
        if (!isEmailAvailable(newEmployee.getEmail()))
            throw new ResourceConflictException();
        if (!isPhoneAvailable(newEmployee.getPhone()))
            throw new ResourceConflictException();
        var employee = new Employee();
        var team = teamRepository.findById(newEmployee.getTeamId())
                .orElseThrow(ResourceNotFoundException::new);
        employee.setTeam(team);
        employeeMapper.updateFromDto(employee, newEmployee);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto update(EmployeeDto newEmployee) {
        var employee = employeeRepository.findById(newEmployee.getId())
                .orElseThrow(ResourceNotFoundException::new);
        if (!(employee.getEmail().equals(newEmployee.getEmail()) || isEmailAvailable(newEmployee.getEmail())))
            throw new ResourceConflictException();
        if (!(employee.getPhone().equals(newEmployee.getPhone()) || isPhoneAvailable(newEmployee.getPhone())))
            throw new ResourceConflictException();
        var team = teamRepository.findById(newEmployee.getTeamId())
                .orElseThrow(ResourceNotFoundException::new);
        employee.setTeam(team);
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
