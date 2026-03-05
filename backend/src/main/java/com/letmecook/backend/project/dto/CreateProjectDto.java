package com.letmecook.backend.project.dto;

import java.util.List;

import com.letmecook.backend.project.Project.PaymentType;
import com.letmecook.backend.project.Project.TechStack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class CreateProjectDto {
    Long userId;
    String title;
    String description;
    PaymentType paymentType;
    List<TechStack> techStack;
}
