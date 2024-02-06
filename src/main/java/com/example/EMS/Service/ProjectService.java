package com.example.EMS.Service;

import com.example.EMS.Entity.Project;
import com.example.EMS.Entity.ProjectStatus;
import com.example.EMS.Repository.ProjectRepository;
import com.example.EMS.Response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public ResponseEntity<?> get() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    public ResponseEntity<?> add(Project project) {
        System.out.println(project);
        Project addedProject = projectRepository.save(project);
        ProjectResponse projectResponse = new ProjectResponse(addedProject, HttpStatus.OK.value(), "", "Project is Added");
        return ResponseEntity.status(HttpStatus.OK).body(projectResponse);
    }

    public ResponseEntity<?> updateById(Long projectId, Project projectDetails) {
        Optional<Project> optionalProject = projectRepository.findById((projectId));
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setProjectName(projectDetails.getProjectName());

            Project updatedProject = projectRepository.save(project);
            ProjectResponse projectResponse = new ProjectResponse(updatedProject, HttpStatus.OK.value(), "", "Project with Id " + projectId + " is updated");
            return ResponseEntity.status(HttpStatus.OK).body(projectResponse);
        } else {
            ProjectResponse projectResponse = new ProjectResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Project with Id " + projectId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(projectResponse);
        }
    }

    public ResponseEntity<?> getById(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            ProjectResponse projectResponse = new ProjectResponse(project, HttpStatus.OK.value(), "", "Project with Id " + projectId + " is Found");
            return ResponseEntity.status(HttpStatus.OK).body(projectResponse);
        } else {
            ProjectResponse projectResponse = new ProjectResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Project with Id " + projectId + " is not exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(projectResponse);
        }
    }

//    public ResponseEntity<?> deleteByStatus(Long projectId) {
//        Optional<Project> optionalProject = projectRepository.findById(projectId);
//        if (optionalProject.isPresent()) {
//            Project project = optionalProject.get();
//            ProjectStatus status = project.getStatus();
//            if (status == ProjectStatus.Ended) {
//                projectRepository.deleteById(projectId);
//                return ResponseEntity.ok().body("Project with ID " + projectId + " has been deleted successfully.");
//            } else {
//                ProjectResponse projectResponse = new ProjectResponse(project, HttpStatus.OK.value(), "", "Project with Id " + projectId + " is Found");
//                return ResponseEntity.status(HttpStatus.OK).body(projectResponse);
//            }
//        } else {
//            ProjectResponse projectResponse = new ProjectResponse(HttpStatus.NOT_FOUND.value(), "Not Found", "Project with Id " + projectId + " does not exist");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(projectResponse);
//        }
//    }

}
