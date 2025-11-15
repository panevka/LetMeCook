package com.letmecook.backend.user;

class UserService {

    User user;

    public User createUser(String username) {
        this.user = User.create(username);
        return this.user;
    }

}
