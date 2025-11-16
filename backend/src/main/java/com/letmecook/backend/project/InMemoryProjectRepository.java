package com.letmecook.backend.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InMemoryProjectRepository implements IProjectRepository {
    private Map<Long, Project> projects = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public List<Project> findAll() {
        List<Project> projectList = projects.values().stream().toList();
        return projectList;
    }

    @Override
    public Project save(Project proj) {
        Project storedProject = proj.withId(idCounter);
        projects.put(idCounter, storedProject);
        idCounter++;
        return storedProject;
    }

    @Override
    public Project getById(Long projectId) {
        return projects.get(projectId);
    }

}
