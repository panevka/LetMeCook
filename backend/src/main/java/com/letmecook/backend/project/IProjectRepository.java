package com.letmecook.backend.project;

import java.util.List;

interface IProjectRepository {
    List<Project> findAll();

    Project getById(Long projectId);

    Project save(Project project);
}
