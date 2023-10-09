package com.vaempunk.stafftool.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String gender;
    private LocalDate birthDate;
    private String country;
    private String city;
    private String address;
    private Long teamId;

}
