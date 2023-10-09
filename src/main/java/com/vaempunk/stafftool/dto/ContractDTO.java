package com.vaempunk.stafftool.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ContractDto {

    private Long id;
    private Long employeeId;
    private Long teamId;
    private String jobName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal salary;

}
