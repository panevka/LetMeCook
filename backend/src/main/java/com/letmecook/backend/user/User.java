package com.letmecook.backend.user;

import lombok.Getter;
import lombok.Value;
import lombok.With;

@Value
@With
class User {

    @Getter
    private final Long id;

    @Getter
    private final String username;

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    private User(Long id, String username, String firstName, String lastName) {
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
            throw new IllegalArgumentException("Last name must be at most 30 characters");
        }

        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    static User create(String username) {
        return new User(null, username, null, null);
    }

    static User create(String username, String firstName) {
        return new User(null, username, firstName, null);
    }

    static User create(String username, String firstName, String lastName) {
        return new User(null, username, firstName, lastName);
    }

    static User create(Long id, String username, String firstName, String lastName) {
        return new User(id, username, firstName, lastName);
    }

}
