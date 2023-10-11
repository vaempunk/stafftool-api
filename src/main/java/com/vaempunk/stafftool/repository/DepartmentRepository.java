package com.vaempunk.stafftool.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Page<Department> findAll(Pageable pageable);

    boolean existsByName(String name);

}
