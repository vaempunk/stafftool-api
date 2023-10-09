package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();

    List<Team> findAllByDepartmentId(long departmentId);

    Team findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, long id);

}
