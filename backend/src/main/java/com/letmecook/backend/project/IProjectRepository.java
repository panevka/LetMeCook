package com.letmecook.backend.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

interface IProjectRepository {
    List<Project> findAll();

    List<Project> findAll(Specification<Project> spec);

    Project getById(Long projectId);

    Optional<Project> findById(Long projectId);

    Project save(Project project);
}
