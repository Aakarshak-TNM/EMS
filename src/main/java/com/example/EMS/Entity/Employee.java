package com.example.EMS.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    double salary;

    @Column(nullable = false)
    String designation;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    long departmentId;

    @Column(nullable = false)
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

//    Wants the id of the projects that the employee of the particular department is handling


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
