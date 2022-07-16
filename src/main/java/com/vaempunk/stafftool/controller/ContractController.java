package com.vaempunk.stafftool.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.vaempunk.stafftool.dto.ContractDTO;
import com.vaempunk.stafftool.entity.Contract;
import com.vaempunk.stafftool.exception.ContractException;
import com.vaempunk.stafftool.exception.EmployeeException;
import com.vaempunk.stafftool.exception.TeamException;
import com.vaempunk.stafftool.service.ContractService;
import com.vaempunk.stafftool.util.mapper.ContractMapper;

@RestController
public class ContractController {

    private final ContractService contractService;
    private final ContractMapper contractMapper;

    public ContractController(ContractService contractService, ContractMapper contractMapper) {
        this.contractService = contractService;
        this.contractMapper = contractMapper;
    }

    @GetMapping("/contracts/{id}")
    public ContractDTO get(@PathVariable Integer id) {

        try {
            Contract contract = contractService.get(id);

            return contractMapper.toDTO(contract);
        } catch (ContractException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }

    @GetMapping("/contracts")
    public List<ContractDTO> getAll() {

        List<Contract> contracts = contractService.getAll();

        return contracts
                .stream()
                .map(contractMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{employeeId}/contracts")
    public List<ContractDTO> getAllByEmployeeId(@PathVariable Integer employeeId) {

        List<Contract> contracts = contractService.getAllByEmployeeId(employeeId);

        return contracts
                .stream()
                .map(contractMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/teams/{teamId}/contracts")
    public List<ContractDTO> getAllByTeamId(@PathVariable Integer teamId) {

        List<Contract> contracts = contractService.getAllByTeamId(teamId);

        return contracts
                .stream()
                .map(contractMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/contracts")
    public ContractDTO add(@RequestBody ContractDTO newContractDTO) {

        try {
            Contract contract = contractService.add(contractMapper.toEntity(newContractDTO));

            return contractMapper.toDTO(contract);
        } catch (EmployeeException | TeamException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }

    @PutMapping("/contracts/{id}")
    public ContractDTO update(@PathVariable Integer id, @RequestBody ContractDTO newContractDTO) {

        try {
            Contract contract = contractService.update(id, contractMapper.toEntity(newContractDTO));

            return contractMapper.toDTO(contract);
        } catch (ContractException | EmployeeException | TeamException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }

    @DeleteMapping("/contracts/{id}")
    public ContractDTO delete(@PathVariable Integer id) {

        try {
            Contract contract = contractService.delete(id);

            return contractMapper.toDTO(contract);
        } catch (ContractException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.toString());
        }
    }
}
