package com.vaempunk.stafftool.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Team> teams = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    public Department() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
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
    //     if (!(obj instanceof Department))
    //         return false;
    //     if (this == obj)
    //         return true;

    //     Department department = (Department) obj;
    //     return id.equals(department.id) &&
    //             teams.equals(department.teams) &&
    //             name.equals(department.name) &&
    //             description.equals(department.description);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, teams, name, description);
    // }

}
