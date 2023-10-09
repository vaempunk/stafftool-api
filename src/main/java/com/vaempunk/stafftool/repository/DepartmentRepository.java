package com.vaempunk.stafftool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();

    Optional<Department> findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, long id);

}
