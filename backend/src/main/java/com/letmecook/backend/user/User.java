package com.letmecook.backend.user;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.With;

@Getter
@Entity
@Table(name = "users")
@With
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String firstName;
    private String lastName;
    private String avatarUrl;

    protected User() {

    }

    private User(Long id, String username, String firstName, String lastName, String avatarUrl) {
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

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
    }

    static User create(String username) {
        return new User(null, username, null, null, null);
    }

    static User create(String username, String firstName) {
        return new User(null, username, firstName, null, null);
    }

    static User create(String username, String firstName, String lastName) {
        return new User(null, username, firstName, lastName, null);
    }

    static User create(String username, String firstName, String lastName, String avatarUrl) {
        return new User(null, username, firstName, lastName, avatarUrl);
    }

}
