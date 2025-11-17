package com.letmecook.backend.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User getUserById(Long userId) {
        return userRepository.getById(userId);
    }

}
