package com.studyplatform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String content;
    private String videoUrl;

    @NotNull(message = "Order index cannot be null")
    private int orderIndex;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @NotNull(message = "Module cannot be null")
    private Module module;
}