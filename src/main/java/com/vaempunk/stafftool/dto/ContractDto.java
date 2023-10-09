package com.vaempunk.stafftool.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContractDto {

    private Long id;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long departmentId;

    @NotBlank
    @Size(max = 255)
    private String jobName;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Max(99999999999999L)
    private BigDecimal salary;

}
