package com.studyplatform.controller;

import com.studyplatform.entity.Quiz;
import com.studyplatform.entity.QuizSubmission;
import com.studyplatform.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService service;

    @PostMapping("/{id}/submit")
    public ResponseEntity<QuizSubmission> submit(@PathVariable Long id, @RequestParam Long userId, @RequestBody Map<Long, List<String>> answers) {
        return ResponseEntity.ok(service.submitQuiz(id, userId, answers));
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody Quiz quiz) {
        return ResponseEntity.ok(service.save(quiz));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return service.findAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}