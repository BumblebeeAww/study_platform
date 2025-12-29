package com.studyplatform.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class CourseDTO {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    private String description;
    private InstructorDTO instructor;
}