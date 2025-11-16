package com.letmecook.backend.project;

import lombok.Data;
import lombok.Value;
import lombok.With;

@Value
@With
class Project {

    private final Long id;
    private final String title;

    private Project(Long id, String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (title.length() > 50) {
            throw new IllegalArgumentException();
        }
        if (title.length() < 5) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.title = title;
    }

    static Project create(String projectName) {
        return new Project(null, projectName);
    }

    static Project create(Long id, String projectName) {
        return new Project(id, projectName);
    }
}
