package com.vaempunk.stafftool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.entity.Employee;
import com.vaempunk.stafftool.exception.EmployeeException;
import com.vaempunk.stafftool.exception.EntityExceptionType;
import com.vaempunk.stafftool.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee get(Integer id)
            throws EmployeeException {

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) {
            throw new EmployeeException(EntityExceptionType.NOT_FOUND);
        }

        return employeeOpt.get();
    }

    public List<Employee> getAll() {

        List<Employee> employees = employeeRepository.findAll();

        return employees;
    }

    public Employee add(Employee newEmployee)
            throws EmployeeException {

        if (employeeRepository.existsByEmail(newEmployee.getEmail()) ||
                employeeRepository.existsByPhoneNumber(newEmployee.getPhoneNumber())) {
            throw new EmployeeException(EntityExceptionType.ALREADY_EXISTS);
        }

        Employee employee = employeeRepository.save(newEmployee);

        return employee;
    }

    public Employee update(Integer id, Employee newEmployee)
            throws EmployeeException {

        if (employeeRepository.existsByEmailAndIdNot(newEmployee.getEmail(), newEmployee.getId()) ||
                employeeRepository.existsByPhoneNumberAndIdNot(newEmployee.getPhoneNumber(), newEmployee.getId())) {
            throw new EmployeeException(EntityExceptionType.ALREADY_EXISTS);
        }

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) {
            throw new EmployeeException(EntityExceptionType.NOT_FOUND);
        }

        Employee employee = employeeOpt.get();
        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setEmail(newEmployee.getEmail());
        employee.setPhoneNumber(newEmployee.getPhoneNumber());
        employee.setGender(newEmployee.getGender());
        employee.setBirthDate(newEmployee.getBirthDate());
        employee.setCountry(newEmployee.getCountry());
        employee.setCity(newEmployee.getCity());
        employee.setAddress(newEmployee.getAddress());
        employeeRepository.save(employee);

        return employee;
    }

    public Employee delete(Integer id)
            throws EmployeeException {

        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) {
            throw new EmployeeException(EntityExceptionType.NOT_FOUND);
        }

        Employee employee = employeeOpt.get();
        employeeRepository.delete(employee);

        return employee;
    }
}
