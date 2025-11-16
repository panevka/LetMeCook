package com.letmecook.backend.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@Entity
@Table(name = "projects")
@With
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    protected Project() {
    }

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
