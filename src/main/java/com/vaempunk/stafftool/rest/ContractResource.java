package com.vaempunk.stafftool.rest;

import java.util.List;

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
    public List<ContractDto> getAll() {
        return contractService.getAll();
    }

    @GetMapping("/employees/{employeeId}/contracts")
    public List<ContractDto> getAllByEmployeeId(@PathVariable("employeeId") Long employeeId) {
        return contractService.getAllByEmployeeId(employeeId);
    }

    @GetMapping("/departments/{departmentId}/contracts")
    public List<ContractDto> getAllByDepartmentId(@PathVariable("departmentId") Long departmentId) {
        return contractService.getAllByDepartmentId(departmentId);
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
