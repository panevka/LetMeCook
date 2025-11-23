package com.letmecook.backend.project;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmecook.backend.project.dto.CreateProjectDto;
import com.letmecook.backend.project.dto.GetProjectsResponseDto;
import com.letmecook.backend.project.dto.JoinProjectDto;

@RestController
@RequestMapping("/api/project")
class ProjectController {

    ProjectService projectService;

    public ProjectController(ProjectService service) {
        this.projectService = service;
    }

    @PostMapping
    Project createProject(@RequestBody CreateProjectDto dto) {
        return projectService.createProject(dto);
    }

    @GetMapping("/all")
    List<GetProjectsResponseDto> getProjects() {
        var projects = projectService.getAllProjects();
        return projects;
    }

    @PostMapping("/join")
    ResponseEntity<String> joinProject(@RequestBody JoinProjectDto dto) {
        projectService.joinProject(dto);
        return ResponseEntity.ok().body("success");
    }

}
