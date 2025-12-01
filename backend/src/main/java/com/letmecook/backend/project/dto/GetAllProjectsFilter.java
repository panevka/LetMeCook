package com.letmecook.backend.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class GetAllProjectsFilter {
    private Long authorId;
}
