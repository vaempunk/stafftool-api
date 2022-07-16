package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Contract;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
    
    List<Contract> findAll();

    List<Contract> findAllByEmployeeId(Integer employeeId);

    List<Contract> findAllByTeamId(Integer teamId);
}
