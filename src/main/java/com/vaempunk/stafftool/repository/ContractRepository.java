package com.vaempunk.stafftool.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.vaempunk.stafftool.entity.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {

    Page<Contract> findAll(Pageable pageable);

    Page<Contract> findAllByEmployeeId(long employeeId, Pageable pageable);

    Page<Contract> findAllByDepartmentId(long departmentId, Pageable pageable);

}
