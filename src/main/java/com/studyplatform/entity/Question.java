package com.studyplatform.entity;

import com.studyplatform.enums.QuestionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Text cannot be blank")
    private String text;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type cannot be null")
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @NotNull(message = "Quiz cannot be null")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Builder.Default
    private List<AnswerOption> options = new ArrayList<>();
}