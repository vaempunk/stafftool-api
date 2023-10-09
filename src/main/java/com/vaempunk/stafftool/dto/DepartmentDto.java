package com.vaempunk.stafftool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDto {

    private Long id;

    @NotBlank
    @Size(max = 64)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

}
