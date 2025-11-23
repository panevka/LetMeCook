package com.letmecook.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PatchUserDto {
    private String firstName;
    private String lastName;
    private String bio;
}
