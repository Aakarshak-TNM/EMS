package com.example.EMS.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Department")
public class Department {

    @Id
    long departmentId;

    @Column(nullable = false)
    String departmentName;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
