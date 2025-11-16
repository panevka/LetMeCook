package com.letmecook.backend.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String firstName, String lastName) {
        User savedUser = userRepository.save(User.create(username, firstName, lastName));
        return savedUser;
    }

    public User createUser(String username, String firstName) {
        User savedUser = userRepository.save(User.create(username, firstName));
        return savedUser;
    }

    public User createUser(String username) {
        User savedUser = userRepository.save(User.create(username));
        return savedUser;
    }

    public User getUserById(Long userId) {
        return userRepository.getById(userId);
    }

}
