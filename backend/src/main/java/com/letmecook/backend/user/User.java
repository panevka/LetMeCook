package com.letmecook.backend.user;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.letmecook.backend.project.Project;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Setter
@Getter
@Entity
@Table(name = "users")
@With
@Builder
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String firstName;
    private String lastName;
    private String avatarUrl;

    @Column(unique = true, nullable = false)
    private BigInteger discordId;

    @ManyToMany(mappedBy = "participants")
    @Builder.Default
    private List<Project> projects = new ArrayList<>();

    private String bio;

    protected User() {

    }

    private User(Long id, String username, String firstName, String lastName, String avatarUrl, BigInteger discordId,
            List<Project> projects, String bio) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (username.length() < 3 || username.length() > 30) {
            throw new IllegalArgumentException("Username must be 3-30 characters");
        }
        if (firstName != null && firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }
        if (firstName != null && firstName.length() < 2) {
            throw new IllegalArgumentException("First name must be at least 2 characters");
        }
        if (firstName != null && firstName.length() > 30) {
            throw new IllegalArgumentException("First name must be at most 30 characters");
        }
        if (lastName != null && lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank");
        }
        if (lastName != null && lastName.length() < 2) {
            throw new IllegalArgumentException("Last name must be at least 2 characters");
        }
        if (lastName != null && lastName.length() > 30) {
            throw new IllegalArgumentException("Last name must be at most 30        characters");
        }
        if (avatarUrl != null && avatarUrl.isBlank()) {
            throw new IllegalArgumentException("Avatar URL cannot be blank");
        }
        if (avatarUrl != null && avatarUrl.isEmpty()) {
            throw new IllegalArgumentException("Avatar URL cannot be empty");
        }
        if (avatarUrl != null && avatarUrl.length() == 0) {
            throw new IllegalArgumentException("Avatar URL cannot be empty");
        }
        if (discordId == null) {
            throw new IllegalArgumentException("Discord ID is required");
        }

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.discordId = discordId;
        this.projects = projects;
        this.bio = bio;
    }

}
