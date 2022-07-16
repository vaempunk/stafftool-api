package com.vaempunk.stafftool.dto;

public class TeamDTO {

    private Integer id;
    private Integer departmentId;
    private String name;
    private String description;

    public TeamDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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
        return "TeamDTO{id=" + id +
                ", departmentId=" + departmentId +
                ", name='" + name +
                "', description='" + description + "'}";
    }

    // @Override
    // public boolean equals(Object obj) {

    //     if (!(obj instanceof TeamDTO))
    //         return false;
    //     if (this == obj)
    //         return true;

    //     TeamDTO teamDTO = (TeamDTO) obj;
    //     return id.equals(teamDTO.id) &&
    //             departmentId.equals(teamDTO.departmentId) &&
    //             name.equals(teamDTO.name) &&
    //             description.equals(teamDTO.description);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, departmentId, name, description);
    // }
}
