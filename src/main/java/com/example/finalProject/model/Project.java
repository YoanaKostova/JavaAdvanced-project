package com.example.finalProject.model;

import org.springframework.data.annotation.Id;

public class Project {

    @Id
    private Long id;
    private String name;
    private Long departmentid;
    //private Department department;


    public Project(String name, Long departmentId) {
        this.name = name;
        this.departmentid = departmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentid;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentid = departmentId;
    }
}
