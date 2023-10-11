package com.vaempunk.stafftool.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaempunk.stafftool.dto.AvailabilityResponse;
import com.vaempunk.stafftool.dto.DepartmentDto;
import com.vaempunk.stafftool.dto.PageDto;
import com.vaempunk.stafftool.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class DepartmentResource {

    private final DepartmentService departmentService;

    @GetMapping("/departments/{id}")
    public DepartmentDto get(@PathVariable("id") Long id) {
        return departmentService.get(id);
    }

    @GetMapping("/departments")
    public PageDto<DepartmentDto> getAll(Pageable pageable) {
        return departmentService.getAll(pageable);
    }

    @PostMapping("/departments")
    @ResponseStatus(code = HttpStatus.CREATED)
    public DepartmentDto add(@RequestBody @Valid DepartmentDto departmentDto) {
        return departmentService.add(departmentDto);
    }

    @PutMapping("/departments/{id}")
    public DepartmentDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid DepartmentDto newDepartmentDto) {
        newDepartmentDto.setId(id);
        return departmentService.update(newDepartmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        departmentService.delete(id);
    }

    @GetMapping("/departments/availability")
    public AvailabilityResponse isPhoneAvailable(@RequestParam(value = "name") String name) {
        var available = departmentService.isDepartmentNameAvailable(name);
        return new AvailabilityResponse(available);
    }

}
