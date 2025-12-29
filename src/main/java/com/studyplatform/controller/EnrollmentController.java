package com.studyplatform.controller;

import com.studyplatform.entity.Enrollment;
import com.studyplatform.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService service;

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@RequestParam Long userId, @RequestParam Long courseId) {
        return ResponseEntity.ok(service.enrollUserInCourse(userId, courseId));
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> getEnrollments(@PathVariable Long userId) {
        return service.findByStudentId(userId);
    }
}