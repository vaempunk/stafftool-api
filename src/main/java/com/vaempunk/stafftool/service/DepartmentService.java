package com.vaempunk.stafftool.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.DepartmentDto;
import com.vaempunk.stafftool.dto.PageDto;
import com.vaempunk.stafftool.entity.Department;
import com.vaempunk.stafftool.exception.ResourceConflictException;
import com.vaempunk.stafftool.exception.ResourceNotFoundException;
import com.vaempunk.stafftool.repository.DepartmentRepository;
import com.vaempunk.stafftool.util.mapper.DepartmentMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentDto get(long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public PageDto<DepartmentDto> getAll(Pageable pageable) {
        var departments = departmentRepository.findAll(pageable)
                .map(departmentMapper::toDto);
        return new PageDto<>(departments.getContent(), departments.getTotalPages(), departments.getNumber());
    }

    public DepartmentDto add(DepartmentDto newDepartment) {
        if (!isDepartmentNameAvailable(newDepartment.getName()))
            throw new ResourceConflictException();
        var department = new Department();
        departmentMapper.updateFromDto(department, newDepartment);
        departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public DepartmentDto update(DepartmentDto newDepartment) {
        var department = departmentRepository.findById(newDepartment.getId())
                .orElseThrow(ResourceNotFoundException::new);
        if (!(department.getName().equals(newDepartment.getName())
                || isDepartmentNameAvailable(newDepartment.getName())))
            throw new ResourceConflictException();
        departmentMapper.updateFromDto(department, newDepartment);
        departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public void delete(long id) {
        if (!departmentRepository.existsById(id))
            throw new ResourceNotFoundException();
        departmentRepository.deleteById(id);
    }

    public boolean isDepartmentNameAvailable(String name) {
        return !departmentRepository.existsByName(name);
    }

}
