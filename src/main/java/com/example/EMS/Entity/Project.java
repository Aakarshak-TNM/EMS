package com.example.EMS.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class Project {

    @Autowired

    long projectId;

    String projectName;

    List<Employee> team;

    Employee teamLead;

    ProjectStatus status;

    Date startdate;

    Date endDate;
}
