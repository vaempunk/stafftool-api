package com.vaempunk.stafftool.dto;

import lombok.Data;

@Data
public class TeamDto {

    private Long id;
    private Long departmentId;
    private String name;
    private String description;

}
