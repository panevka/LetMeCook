package com.letmecook.backend.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.letmecook.backend.project.dto.CreateProjectDto;

@Service
class ProjectService {

    IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    Project createProject(CreateProjectDto dto) {
        var project = Project.create(dto.getTitle());
        var createdProject = projectRepository.save(project);
        return createdProject;
    }

    List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    Project getProjectById(Long projectId) {
        return projectRepository.getById(projectId);
    }
}
