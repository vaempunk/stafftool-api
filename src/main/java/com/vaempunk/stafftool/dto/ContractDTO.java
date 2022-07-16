package com.vaempunk.stafftool.dto;

import java.time.LocalDate;

public class ContractDTO {
    
    private Integer id;
    private Integer employeeId;
    private Integer teamId;
    private String jobName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double salary;

    public ContractDTO() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public String getJobName() {
        return jobName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ContractDTO{id=" + id +
        ", employeeId=" + employeeId +
        ", teamId=" + teamId +
        ", jobName='" + jobName +
        "', startDate='" + startDate +
        "', endDate='" + endDate +
        "', salary=" + salary;
    }
}
