package com.letmecook.backend.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.letmecook.backend.user.dto.GetUserDto;

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

    public Optional<User> getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional;
    }

    public GetUserDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        GetUserDto dto = GetUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarUrl(user.getAvatarUrl())
                .build();

        return dto;
    }

}
