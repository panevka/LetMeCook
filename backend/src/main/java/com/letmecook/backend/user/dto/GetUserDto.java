package com.letmecook.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GetUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String bio;
}
