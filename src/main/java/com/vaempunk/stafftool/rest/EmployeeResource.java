package com.vaempunk.stafftool.rest;

import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
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
import com.vaempunk.stafftool.dto.EmployeeDto;
import com.vaempunk.stafftool.dto.PageDto;
import com.vaempunk.stafftool.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmployeeResource {

    private final EmployeeService employeeService;

    @GetMapping("/employees/{id}")
    public EmployeeDto get(@PathVariable("id") Long id) {
        return employeeService.get(id);
    }

    @GetMapping("/employees")
    public PageDto<EmployeeDto> getAll(@ParameterObject Pageable pageable) {
        return employeeService.getAll(pageable);
    }

    @GetMapping("/teams/{teamId}/employees")
    public PageDto<EmployeeDto> getAllByTeamId(
            @PathVariable("teamId") Long teamId,
            @ParameterObject Pageable pageable) {
        return employeeService.getAllByTeamId(teamId, pageable);
    }                

    @PostMapping("/employees")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeDto add(@RequestBody @Valid EmployeeDto employeeDto) {
        return employeeService.add(employeeDto);
    }

    @PutMapping("/employees/{id}")
    public EmployeeDto update(
            @PathVariable("id") Long id,
            @RequestBody @Valid EmployeeDto employeeDto) {
        employeeDto.setId(id);
        return employeeService.update(employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

    @GetMapping("/employees/availability")
    public AvailabilityResponse isPhoneOrEmailAvailable(
            @RequestParam(value = "phone", required = false) Optional<String> phone,
            @RequestParam(value = "email", required = false) Optional<String> email) {
        if (phone.isPresent() && !employeeService.isPhoneAvailable(phone.get())) {
            return new AvailabilityResponse(false);
        }
        if (email.isPresent() && !employeeService.isEmailAvailable(email.get())) {
            return new AvailabilityResponse(false);    
        }
        return new AvailabilityResponse(true);
    }

}
