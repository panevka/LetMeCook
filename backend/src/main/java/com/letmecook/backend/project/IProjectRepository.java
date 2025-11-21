package com.letmecook.backend.project;

import java.util.List;
import java.util.Optional;

interface IProjectRepository {
    List<Project> findAll();

    Project getById(Long projectId);

    Optional<Project> findById(Long projectId);

    Project save(Project project);
}
