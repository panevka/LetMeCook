package com.letmecook.backend.project;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;

import com.letmecook.backend.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@Entity
@Table(name = "projects")
@With
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Convert(converter = TechStackListConverter.class)
    @Column(nullable = false)
    @Builder.Default
    private List<TechStack> techStack = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(name = "project_participants", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private List<User> participants = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected Project() {
    }

    private Project(Long id, String title, String description, PaymentType paymentType, List<TechStack> techStack,
            User author,
            List<User> participants, Instant createdAt) {
        if (title == null) {
            throw new IllegalArgumentException();
        }
        if (title.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (title.length() > 50) {
            throw new IllegalArgumentException();
        }
        if (title.length() < 5) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.paymentType = paymentType;
        this.techStack = techStack;
        this.author = author;
        this.participants = participants;
        this.createdAt = createdAt;
    }

    public enum PaymentType {
        MONEY,
        GRATITUDE,
        EQUITY,
        SYMBOLIC,
        VOUCHER,
    }

    public enum TechStack {
        REACT,
        SPRING,
        KOTLIN,
        HASKELL,
        JULIA,
        ADA,
        SCALA,
        OCAML,
    }

}
