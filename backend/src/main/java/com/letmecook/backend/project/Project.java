package com.letmecook.backend.project;

import java.util.List;
import java.util.ArrayList;

import com.letmecook.backend.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToMany
    @JoinTable(name = "project_participants", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private List<User> participants = new ArrayList<>();

    protected Project() {
    }

    private Project(Long id, String title, User author, List<User> participants) {
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
        this.author = author;
        this.participants = participants;
    }

}
