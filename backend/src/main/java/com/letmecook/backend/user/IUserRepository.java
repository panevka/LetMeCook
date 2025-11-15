package com.letmecook.backend.user;

interface IUserRepository {
    User save(User user);

    User getById(Long id);
}
