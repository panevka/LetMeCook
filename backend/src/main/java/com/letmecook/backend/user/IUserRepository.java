package com.letmecook.backend.user;

import java.util.Optional;

interface IUserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByDiscordId(Long id);
}
