package com.letmecook.backend.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class GetProjectsResponseDto {
    Long id;
    String title;
    Long authorId;
}
