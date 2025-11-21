package com.letmecook.backend.project;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letmecook.backend.project.dto.CreateProjectDto;
import com.letmecook.backend.project.dto.GetProjectsResponseDto;

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

        return projects.stream().map(project -> {
            var dto = new GetProjectsResponseDto();

            dto.setTitle(project.getTitle());
            dto.setId(project.getAuthor().getId());
            dto.setAuthorId(project.getAuthor().getId());

            return dto;
        }).toList();
    }
}
