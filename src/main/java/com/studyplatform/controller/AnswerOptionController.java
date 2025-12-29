package com.studyplatform.controller;

import com.studyplatform.entity.AnswerOption;
import com.studyplatform.service.AnswerOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer-options")
@RequiredArgsConstructor
public class AnswerOptionController {
    private final AnswerOptionService service;

    @PostMapping
    public ResponseEntity<AnswerOption> createAnswerOption(@RequestBody AnswerOption answerOption) {
        return ResponseEntity.ok(service.save(answerOption));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerOption> getAnswerOption(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AnswerOption> getAllAnswerOptions() {
        return service.findAll();
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerOption> getAnswerOptionsByQuestion(@PathVariable Long questionId) {
        return service.findByQuestionId(questionId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerOption> updateAnswerOption(@PathVariable Long id, @RequestBody AnswerOption answerOption) {
        answerOption.setId(id);
        return ResponseEntity.ok(service.save(answerOption));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}