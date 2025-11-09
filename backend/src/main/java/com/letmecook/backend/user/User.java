package com.letmecook.backend.user;

class User {

    private final String username;
    private final String firstName;
    private final String lastName;

    private User(String username, String firstName, String lastName) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (username.length() < 3 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be 3-20 characters");
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

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    static User create(String username) {
        return new User(username, null, null);
    }

    static User create(String username, String firstName) {
        return new User(username, firstName, null);
    }

    static User create(String username, String firstName, String lastName) {
        return new User(username, firstName, lastName);
    }

    String getUsername() {
        return this.username;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

}
