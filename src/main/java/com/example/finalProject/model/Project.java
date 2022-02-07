package com.example.finalProject.model;

import org.springframework.data.annotation.Id;

public class Project {

    @Id
    private Long id;
    private String name;
    private Long departmentid;


    public Project(String name, Long departmentid) {
        this.name = name;
        this.departmentid = departmentid;
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

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }
}
