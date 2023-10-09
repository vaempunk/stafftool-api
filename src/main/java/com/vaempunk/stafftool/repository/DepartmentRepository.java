package com.vaempunk.stafftool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    public List<Department> findAll();

    public Optional<Department> findByName(String name);

    public boolean existsByName(String name);

    public boolean existsByNameAndIdNot(String name, long id);
}
