package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();

    List<Team> findAllByDepartmentId(long departmentId);

    boolean existsByDepartmentIdAndNameIgnoreCase(long departmentId, String name);

}
