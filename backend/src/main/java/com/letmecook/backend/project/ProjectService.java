package com.letmecook.backend.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.letmecook.backend.project.dto.CreateProjectDto;
import com.letmecook.backend.project.dto.GetAllProjectsFilter;
import com.letmecook.backend.project.dto.GetProjectsResponseDto;
import com.letmecook.backend.project.dto.JoinProjectDto;
import com.letmecook.backend.user.User;
import com.letmecook.backend.user.UserService;
import com.letmecook.backend.user.User_;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                                .orElseThrow(() -> new IllegalArgumentException(
                                                "User not found with id: " + dto.getUserId()));

                var project = Project.builder()
                                .title(dto.getTitle())
                                .author(user)
                                .description(dto.getDescription())
                                .paymentType(dto.getPaymentType())
                                .techStack(dto.getTechStack())
                                .build();

                var createdProject = projectRepository.save(project);
                return createdProject;
        }

        @Transactional
        void joinProject(JoinProjectDto dto) {
                Project project = projectRepository.findById(dto.getProjectId())
                                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " +
                                                dto.getProjectId()));

                User user = userService.getUserById(dto.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " +
                                                dto.getUserId()));

                project.getParticipants().add(user);
                user.getProjects().add(project);
        }

        List<GetProjectsResponseDto> getAllProjects(GetAllProjectsFilter filters) {
                Specification<Project> spec = Specification.unrestricted();

                if (filters.getAuthorId() != null) {
                        spec = spec.and((root, query, cb) -> cb.equal(root.get(Project_.author).get(User_.id),
                                        filters.getAuthorId()));
                }

                return projectRepository.findAll(spec).stream()
                                .map(project -> GetProjectsResponseDto.builder()
                                                .id(project.getId())
                                                .title(project.getTitle())
                                                .description(project.getDescription())
                                                .paymentType(project.getPaymentType())
                                                .techStack(project.getTechStack())
                                                .authorUsername(project.getAuthor().getUsername())
                                                .authorId(project.getAuthor().getId())
                                                .createdAt(project.getCreatedAt())
                                                .build())
                                .toList();
        }

        Project getProjectById(Long projectId) {
                return projectRepository.getById(projectId);
        }

        void deleteProjectById(Long projectId) {
                projectRepository.deleteById(projectId);
        }

        List<GetProjectsResponseDto> searchProjects(String phrase) {
                return projectRepository.findByTitleContaining(phrase).stream()
                                .map(project -> GetProjectsResponseDto.builder()
                                                .id(project.getId())
                                                .title(project.getTitle())
                                                .description(project.getDescription())
                                                .paymentType(project.getPaymentType())
                                                .techStack(project.getTechStack())
                                                .authorUsername(project.getAuthor().getUsername())
                                                .authorId(project.getAuthor().getId())
                                                .createdAt(project.getCreatedAt())
                                                .build())
                                .toList();
        }
}
