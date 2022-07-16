package com.vaempunk.stafftool.dto;

public class DepartmentDTO {

    private Integer id;
    private String name;
    private String description;

    public DepartmentDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{id=" + id +
                ", name='" + name +
                "', description='" + description + "'}";
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (!(obj instanceof DepartmentDTO))
    //         return false;
    //     if (this == obj)
    //         return true;

    //     DepartmentDTO departmentDTO = (DepartmentDTO) obj;
    //     return id.equals(departmentDTO.id) &&
    //             name.equals(departmentDTO.name) &&
    //             description.equals(departmentDTO.description);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, name, description);
    // }
}
