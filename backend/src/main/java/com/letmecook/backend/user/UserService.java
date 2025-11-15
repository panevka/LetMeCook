package com.letmecook.backend.user;

class UserService {

    User user;

    public User createUser(String username, String firstName, String lastName) {
        this.user = User.create(username, firstName, lastName);
        return this.user;
    }

    public User createUser(String username, String firstName) {
        this.user = User.create(username, firstName);
        return this.user;
    }

    public User createUser(String username) {
        this.user = User.create(username);
        return this.user;
    }
}
