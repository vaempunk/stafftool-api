package com.vaempunk.stafftool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

    List<Contract> findAll();

    List<Contract> findAllByEmployeeId(long employeeId);

    List<Contract> findAllByDepartmentId(long departmentId);

}
