package com.letmecook.backend.project.dto;

import java.time.Instant;
import java.util.List;

import com.letmecook.backend.project.Project.PaymentType;
import com.letmecook.backend.project.Project.TechStack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class GetProjectsResponseDto {
    Long id;
    String title;
    String description;
    List<TechStack> techStack;
    PaymentType paymentType;
    Long authorId;
    String authorUsername;
    Instant createdAt;
}
