package com.letmecook.backend.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;

    UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String getAvatarUrl(String avatarId, String userId) {
        if (avatarId == null) {
            return "https://cdn.discordapp.com/embed/avatars/0.png";
        }

        String avatarUrl = String.format("https://cdn.discordapp.com/avatars/%s/%s.png", userId, avatarId);

        return avatarUrl;
    }

    public User createUser(OAuth2UserDiscordDto dto) {

        String avatarUrl = getAvatarUrl(dto.getAvatarId(), dto.getId().toString());

        User user = User.builder()
                .discordId(dto.getId())
                .username(dto.getGlobalName())
                .avatarUrl(avatarUrl)
                .build();

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User getUserById(Long userId) {
        return userRepository.getById(userId);
    }

}
