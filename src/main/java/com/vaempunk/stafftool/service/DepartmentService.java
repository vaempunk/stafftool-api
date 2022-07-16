package com.vaempunk.stafftool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.entity.Department;
import com.vaempunk.stafftool.exception.DepartmentException;
import com.vaempunk.stafftool.exception.EntityExceptionType;
import com.vaempunk.stafftool.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department get(Integer id)
            throws DepartmentException {

        Optional<Department> departmentOpt = departmentRepository.findById(id);
        if (departmentOpt.isEmpty()) {
            throw new DepartmentException(EntityExceptionType.NOT_FOUND);
        }

        return departmentOpt.get();
    }

    public List<Department> getAll() {

        List<Department> departments = departmentRepository.findAll();

        return departments;
    }

    public Department add(Department newDepartment)
            throws DepartmentException {

        if (departmentRepository.existsByName(newDepartment.getName())) {
            throw new DepartmentException(EntityExceptionType.ALREADY_EXISTS);
        }

        Department department = departmentRepository.save(newDepartment);

        return department;
    }

    public Department update(Integer id, Department newDepartment)
            throws DepartmentException {

        if (departmentRepository.existsByNameAndIdNot(newDepartment.getName(), id)) {
            throw new DepartmentException(EntityExceptionType.ALREADY_EXISTS);
        }

        Optional<Department> departmentOpt = departmentRepository.findById(id);
        if (departmentOpt.isEmpty()) {
            throw new DepartmentException(EntityExceptionType.NOT_FOUND);
        }

        Department department = departmentOpt.get();
        department.setName(newDepartment.getName());
        department.setDescription(newDepartment.getDescription());
        departmentRepository.save(department);

        return department;
    }

    public Department delete(Integer id)
            throws DepartmentException {

        Optional<Department> departmentOpt = departmentRepository.findById(id);
        if (departmentOpt.isEmpty()) {
            throw new DepartmentException(EntityExceptionType.NOT_FOUND);
        }

        Department department = departmentOpt.get();
        departmentRepository.delete(department);

        return department;
    }
}
