package com.letmecook.backend.project;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmecook.backend.project.dto.CreateProjectDto;

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

}
