package com.letmecook.backend.project;

import lombok.Data;

@Data
class Project {

    private final String title;

    private Project(String title) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
    }

    static Project create(String projectName) {
        return new Project(projectName);
    }

}
