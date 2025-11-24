package com.letmecook.backend.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryUserRepository implements IUserRepository {
    private final Map<Long, User> usersDb = new HashMap<>();
    private Long currentId = 0L;

    @Override
    public User save(User user) {
        Long id = currentId++;
        User storedUser = user.withId(id);
        usersDb.put(id, storedUser);
        return storedUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(usersDb.get(id));
    }

    @Override
    public Optional<User> findByDiscordId(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findByDiscordId'");
    }

}
