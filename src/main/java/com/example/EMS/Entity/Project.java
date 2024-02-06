package com.example.EMS.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Project")
@Data
public class Project {


    @Id
    private long projectId;

    @Column(nullable = false)
    private String projectName;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    List<Employee> team;

    @ManyToOne
    @JoinColumn(name = "team_lead_id")
    Employee teamLead;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }

    public Employee getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(Employee teamLead) {
        this.teamLead = teamLead;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
