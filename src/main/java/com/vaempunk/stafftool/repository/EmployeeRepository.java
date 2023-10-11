package com.vaempunk.stafftool.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findAllByTeamId(long teamId, Pageable pageable);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
