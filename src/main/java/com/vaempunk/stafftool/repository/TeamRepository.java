package com.vaempunk.stafftool.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Page<Team> findAll(Pageable pageable);

    Page<Team> findAllByDepartmentId(long departmentId, Pageable pageable);

    boolean existsByDepartmentIdAndNameIgnoreCase(long departmentId, String name);

}
