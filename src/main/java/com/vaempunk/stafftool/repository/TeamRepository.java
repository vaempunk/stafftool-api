package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    public List<Team> findAll();

    public List<Team> findAllByDepartmentId(long departmentId);

    public Team findByName(String name);

    public boolean existsByName(String name);

    public boolean existsByNameAndIdNot(String name, long id);
}
