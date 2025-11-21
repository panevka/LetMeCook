package com.letmecook.backend.project.dto;

import java.util.List;

import com.letmecook.backend.project.Project.PaymentType;
import com.letmecook.backend.project.Project.TechStack;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class GetProjectsResponseDto {
    Long id;
    String title;
    String description;
    List<TechStack> techStack;
    PaymentType paymentType;
    Long authorId;
}
