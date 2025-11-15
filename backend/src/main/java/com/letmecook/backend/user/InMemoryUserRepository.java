package com.letmecook.backend.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
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
    public User getById(Long id) {
        return usersDb.get(id);
    }

}
