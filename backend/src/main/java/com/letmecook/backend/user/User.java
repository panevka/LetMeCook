package com.letmecook.backend.user;

class User {

    private final String username;

    private User(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or blank");
        }
        if (username.length() < 3 || username.length() > 20) {
            throw new IllegalArgumentException("Username must be 3-20 characters");
        }

        this.username = username;
    }

    static User create(String username) {
        return new User(username);
    }

    String getUsername() {
        return this.username;
    }

}
