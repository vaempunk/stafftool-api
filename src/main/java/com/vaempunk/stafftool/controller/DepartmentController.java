package com.vaempunk.stafftool.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vaempunk.stafftool.dto.DepartmentDTO;
import com.vaempunk.stafftool.entity.Department;
import com.vaempunk.stafftool.exception.DepartmentException;
import com.vaempunk.stafftool.service.DepartmentService;
import com.vaempunk.stafftool.util.mapper.DepartmentMapper;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    // @Autowired
    public DepartmentController(DepartmentService departmentService, DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @GetMapping("/{id}")
    public DepartmentDTO get(@PathVariable Integer id) {

        try {
            Department department = departmentService.get(id);

            return departmentMapper.toDTO(department);
        } catch (DepartmentException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }

    @GetMapping
    public List<DepartmentDTO> getAll() {

        List<Department> departments = departmentService.getAll();

        return departments
                .stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DepartmentDTO add(@RequestBody DepartmentDTO departmentDTO) {

        try {
            Department department = departmentService.add(departmentMapper.toEntity(departmentDTO));

            return departmentMapper.toDTO(department);
        } catch (DepartmentException exc) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, exc.toString());
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.toString());
        }
    }

    @PutMapping("/{id}")
    public DepartmentDTO update(@PathVariable Integer id, @RequestBody DepartmentDTO newDepartmentDTO) {

        try {
            Department department = departmentService.update(id, departmentMapper.toEntity(newDepartmentDTO));

            return departmentMapper.toDTO(department);
        } catch (DepartmentException exc) {

            switch (exc.getType()) {
                case NOT_FOUND:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
                case ALREADY_EXISTS:
                    throw new ResponseStatusException(HttpStatus.CONFLICT, exc.toString());
                default:
                    return null;
            }
        } catch (DataIntegrityViolationException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.toString());
        }
    }

    @DeleteMapping("/{id}")
    public DepartmentDTO delete(@PathVariable Integer id) {

        try {
            Department department = departmentService.delete(id);

            return departmentMapper.toDTO(department);
        } catch (DepartmentException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }
}
