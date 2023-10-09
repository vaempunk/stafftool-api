package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();

    boolean existsByName(String name);

}
