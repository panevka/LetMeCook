package com.letmecook.backend.project;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.letmecook.backend.project.dto.CreateProjectDto;
import com.letmecook.backend.user.User;
import com.letmecook.backend.user.UserService;

import jakarta.transaction.Transactional;

@Service
class ProjectService {

    IProjectRepository projectRepository;
    UserService userService;

    public ProjectService(IProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Transactional
    Project createProject(CreateProjectDto dto) {
        User user = userService.getUserById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + dto.getUserId()));

        var project = Project.builder()
                .title(dto.getTitle())
                .author(user)
                .build();

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
