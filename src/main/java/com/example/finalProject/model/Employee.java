package com.example.finalProject.model;

import org.springframework.data.annotation.Id;

public class Employee {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String address;
    private double salary;
    private Long departmentid;

    public Employee(){}

    public Employee(String firstname, String lastname, String address, double salary, Long departmentid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.salary = salary;
        this.departmentid = departmentid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", departmentid=" + departmentid +
                '}';
    }
}
