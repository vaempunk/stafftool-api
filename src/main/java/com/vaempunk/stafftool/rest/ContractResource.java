package com.vaempunk.stafftool.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaempunk.stafftool.dto.ContractDto;
import com.vaempunk.stafftool.dto.PageDto;
import com.vaempunk.stafftool.service.ContractService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ContractResource {

    private final ContractService contractService;

    @GetMapping("/contracts/{id}")
    public ContractDto get(@PathVariable("id") Long id) {
        return contractService.get(id);
    }

    @GetMapping("/contracts")
    public PageDto<ContractDto> getAll(Pageable pageable) {
        return contractService.getAll(pageable);
    }

    @GetMapping("/employees/{employeeId}/contracts")
    public PageDto<ContractDto> getAllByEmployeeId(@PathVariable("employeeId") Long employeeId, Pageable pageable) {
        return contractService.getAllByEmployeeId(employeeId, pageable);
    }

    @GetMapping("/departments/{departmentId}/contracts")
    public PageDto<ContractDto> getAllByDepartmentId(@PathVariable("departmentId") Long departmentId, Pageable pageable) {
        return contractService.getAllByDepartmentId(departmentId, pageable);
    }

    @PostMapping("/contracts")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ContractDto add(@RequestBody @Valid ContractDto newContractDto) {
        return contractService.add(newContractDto);
    }

    @PutMapping("/contracts/{id}")
    public ContractDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid ContractDto newContractDto) {
        newContractDto.setId(id);
        return contractService.update(newContractDto);
    }

    @DeleteMapping("/contracts/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        contractService.delete(id);
    }

}
