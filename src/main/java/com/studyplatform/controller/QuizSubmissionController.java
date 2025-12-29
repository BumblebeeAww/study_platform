package com.studyplatform.controller;

import com.studyplatform.entity.QuizSubmission;
import com.studyplatform.service.QuizSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-submissions")
@RequiredArgsConstructor
public class QuizSubmissionController {
    private final QuizSubmissionService service;

    @PostMapping
    public ResponseEntity<QuizSubmission> createQuizSubmission(@RequestBody QuizSubmission quizSubmission) {
        return ResponseEntity.ok(service.save(quizSubmission));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizSubmission> getQuizSubmission(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<QuizSubmission> getAllQuizSubmissions() {
        return service.findAll();
    }

    @GetMapping("/student/{studentId}")
    public List<QuizSubmission> getQuizSubmissionsByStudent(@PathVariable Long studentId) {
        return service.findByStudentId(studentId);
    }

    @GetMapping("/quiz/{quizId}/student/{studentId}")
    public ResponseEntity<QuizSubmission> getQuizSubmissionByQuizAndStudent(@PathVariable Long quizId, @PathVariable Long studentId) {
        return service.findByQuizIdAndStudentId(quizId, studentId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuizSubmission> getQuizSubmissionsByQuiz(@PathVariable Long quizId) {
        return service.findByQuizId(quizId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizSubmission> updateQuizSubmission(@PathVariable Long id, @RequestBody QuizSubmission quizSubmission) {
        quizSubmission.setId(id);
        return ResponseEntity.ok(service.save(quizSubmission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizSubmission(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}