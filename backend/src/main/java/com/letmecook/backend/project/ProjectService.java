package com.letmecook.backend.project;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
class ProjectService {

    IProjectRepository projectRepository;

    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    Project createProject(String projectTitle) {
        var project = Project.create(projectTitle);
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
