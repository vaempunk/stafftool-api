package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    public List<Team> findAll();

    public List<Team> findAllByDepartmentId(Integer departmentId);

    public Team findByName(String name);

    public boolean existsByName(String name);

    public boolean existsByNameAndIdNot(String name, Integer id);
}
