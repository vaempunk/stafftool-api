package com.vaempunk.stafftool.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDto {

    private Long id;

    @NotNull
    private Long teamId;

    @NotBlank
    @Size(max = 64)
    private String firstname;

    @NotBlank
    @Size(max = 64)
    private String lastname;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 64)
    private String phone;

    @NotBlank
    @Pattern(regexp = "^(M|F)$")
    private String gender;

    private LocalDate birthDate;

    private String country;

    private String city;

    private String address;

}
