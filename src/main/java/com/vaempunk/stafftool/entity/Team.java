package com.vaempunk.stafftool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Department department;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    public Team() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    // @Override
    // public boolean equals(Object obj) {
    //     if (!(obj instanceof Team))
    //         return false;
    //     if (this == obj)
    //         return true;

    //     Team team = (Team) obj;
    //     return id.equals(team.id) &&
    //             department.equals(team.department) &&
    //             name.equals(team.name) &&
    //             description.equals(team.description);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, department, name, description);
    // }
}
