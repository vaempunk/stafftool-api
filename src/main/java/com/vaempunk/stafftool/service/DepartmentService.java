package com.vaempunk.stafftool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.DepartmentDto;
import com.vaempunk.stafftool.entity.Department;
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

    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentDto add(DepartmentDto newDepartment) {
        var department = new Department();
        departmentMapper.updateFromDto(department, newDepartment);
        departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public DepartmentDto update(DepartmentDto newDepartment) {
        var department = departmentRepository.findById(newDepartment.getId())
                .orElseThrow(ResourceNotFoundException::new);
        departmentMapper.updateFromDto(department, newDepartment);
        departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    public void delete(long id) {
        if (!departmentRepository.existsById(id))
            throw new ResourceNotFoundException();
        departmentRepository.deleteById(id);
    }

}
