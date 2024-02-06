package com.example.EMS.Controller;

import com.example.EMS.Entity.Project;
import com.example.EMS.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/fetchProject")
    public ResponseEntity<?> fetchProject() {
        return projectService.get();
    }

    @PostMapping("/createProject")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        return projectService.add(project);
    }

    @GetMapping("/findProject/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable(value = "id") Long projectId) {
        return projectService.getById(projectId);
    }

    @PutMapping("/updateProject/{id}")
    public ResponseEntity<?> updateProjectById(@PathVariable(value = "id") Long projectId, @RequestBody Project projectDetails) {
        return projectService.updateById(projectId, projectDetails);
    }

//    @DeleteMapping("/deleteByStatus/{id}")
//    public ResponseEntity<?> deleteByStatus(@PathVariable(value = "id") Long projectId) {
//        return projectService.deleteByStatus(projectId);
//    }
}
