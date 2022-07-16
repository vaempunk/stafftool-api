package com.vaempunk.stafftool.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vaempunk.stafftool.dto.EmployeeDTO;
import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.exception.EmployeeException;
import com.vaempunk.stafftool.service.EmployeeService;
import com.vaempunk.stafftool.util.mapper.EmployeeMapper;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping("/{id}")
    public EmployeeDTO get(@PathVariable Integer id) {

        try {
            Employee employee = employeeService.get(id);

            return employeeMapper.toDTO(employee);
        } catch (EmployeeException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {

        List<Employee> employees = employeeService.getAll();

        return employees
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeDTO add(@RequestBody EmployeeDTO employeeDTO) {

        try {
            Employee employee = employeeService.add(employeeMapper.toEntity(employeeDTO));

            return employeeMapper.toDTO(employee);
        } catch (EmployeeException exc) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, exc.toString());
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.toString());
        }
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(
            @PathVariable Integer id,
            @RequestBody EmployeeDTO employeeDTO) {

        try {
            Employee employee = employeeService.update(id, employeeMapper.toEntity(employeeDTO));

            return employeeMapper.toDTO(employee);
        } catch (EmployeeException exc) {

            switch (exc.getType()) {
                case NOT_FOUND:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
                case ALREADY_EXISTS:
                    throw new ResponseStatusException(HttpStatus.CONFLICT, exc.toString());
                default:
                    return null;
            }
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.toString());
        }
    }

    @DeleteMapping("{id}")
    public EmployeeDTO delete(@PathVariable Integer id) {

        try {
            Employee employee = employeeService.delete(id);

            return employeeMapper.toDTO(employee);
        } catch (EmployeeException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }
}
