package com.vaempunk.stafftool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.dto.ContractDto;
import com.vaempunk.stafftool.entity.Contract;
import com.vaempunk.stafftool.exception.ResourceNotFoundException;
import com.vaempunk.stafftool.repository.ContractRepository;
import com.vaempunk.stafftool.repository.EmployeeRepository;
import com.vaempunk.stafftool.repository.TeamRepository;
import com.vaempunk.stafftool.util.mapper.ContractMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final ContractMapper contractMapper;

    public ContractDto get(long id) {
        return contractRepository.findById(id)
                .map(contractMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<ContractDto> getAll() {
        return contractRepository.findAll().stream()
                .map(contractMapper::toDto)
                .toList();
    }

    public List<ContractDto> getAllByEmployeeId(long employeeId) {
        return contractRepository.findAllByEmployeeId(employeeId).stream()
                .map(contractMapper::toDto)
                .toList();
    }

    public List<ContractDto> getAllByTeamId(long teamId) {
        return contractRepository.findAllByTeamId(teamId).stream()
                .map(contractMapper::toDto)
                .toList();
    }

    public ContractDto add(ContractDto newContract) {
        var contract = new Contract();
        contractMapper.updateFromDto(contract, newContract);
        var team = teamRepository.findById(newContract.getTeamId())
                .orElseThrow(ResourceNotFoundException::new);
        contract.setTeam(team);
        var employee = employeeRepository.findById(newContract.getEmployeeId())
                .orElseThrow(ResourceNotFoundException::new);
        contract.setEmployee(employee);
        contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    public ContractDto update(ContractDto updatedContract) {
        var contract = contractRepository.findById(updatedContract.getId())
                .orElseThrow(ResourceNotFoundException::new);
        contractMapper.updateFromDto(contract, updatedContract);
        var team = teamRepository.findById(updatedContract.getTeamId())
                .orElseThrow(ResourceNotFoundException::new);
        contract.setTeam(team);
        var employee = employeeRepository.findById(updatedContract.getEmployeeId())
                .orElseThrow(ResourceNotFoundException::new);
        contract.setEmployee(employee);
        contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    public void delete(long id) {
        if (!contractRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        contractRepository.deleteById(id);
    }
}
