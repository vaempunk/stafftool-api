package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    public List<Employee> findAll();
    public boolean existsByEmail(String email);
    public boolean existsByPhoneNumber(String phoneNumber);
    public boolean existsByEmailAndIdNot(String email, Integer id);
    public boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
