package com.studyplatform.controller;

import com.studyplatform.entity.Submission;
import com.studyplatform.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService service;

    @PostMapping("/{id}/submit")
    public ResponseEntity<Submission> submit(@PathVariable Long id, @RequestParam Long userId, @RequestParam String content) {
        return ResponseEntity.ok(service.submitAssignment(id, userId, content));
    }
}