package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    List<Employee> findAllByTeamId(long teamId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
