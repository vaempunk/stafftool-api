package com.vaempunk.stafftool.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaempunk.stafftool.entity.Contract;
import com.vaempunk.stafftool.exception.ContractException;
import com.vaempunk.stafftool.exception.EntityExceptionType;
import com.vaempunk.stafftool.repository.ContractRepository;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {

        this.contractRepository = contractRepository;
    }

    public Contract get(Integer id) throws ContractException {

        Optional<Contract> contractOpt = contractRepository.findById(id);
        if (contractOpt.isEmpty()) {
            throw new ContractException(EntityExceptionType.NOT_FOUND);
        }

        return contractOpt.get();
    }

    public List<Contract> getAll() {

        List<Contract> contracts = contractRepository.findAll();

        return contracts;
    }

    public List<Contract> getAllByEmployeeId(Integer employeeId) {

        return contractRepository.findAllByEmployeeId(employeeId);
    }

    public List<Contract> getAllByTeamId(Integer teamId) {

        return contractRepository.findAllByTeamId(teamId);
    }

    public Contract add(Contract newContract) {

        Contract contract = contractRepository.save(newContract);

        return contract;
    }

    public Contract update(Integer id, Contract newContract)
            throws ContractException {

        Optional<Contract> contractOpt = contractRepository.findById(id);
        if (contractOpt.isEmpty()) {
            throw new ContractException(EntityExceptionType.NOT_FOUND);
        }

        Contract contract = contractOpt.get();
        contract.setEmployee(newContract.getEmployee());
        contract.setTeam(newContract.getTeam());
        contract.setJobName(newContract.getJobName());
        contract.setStartDate(newContract.getStartDate());
        contract.setEndDate(newContract.getEndDate());
        contractRepository.save(contract);

        return contract;
    }

    public Contract delete(Integer id) throws ContractException {

        Optional<Contract> contractOpt = contractRepository.findById(id);
        if (contractOpt.isEmpty()) {
            throw new ContractException(EntityExceptionType.NOT_FOUND);
        }

        Contract contract = contractOpt.get();
        contractRepository.delete(contract);

        return contract;
    }
}
