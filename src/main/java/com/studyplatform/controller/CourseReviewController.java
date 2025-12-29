package com.studyplatform.controller;

import com.studyplatform.entity.CourseReview;
import com.studyplatform.service.CourseReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class CourseReviewController {
    private final CourseReviewService service;

    @PostMapping
    public ResponseEntity<CourseReview> createReview(@RequestBody CourseReview review) {
        return ResponseEntity.ok(service.save(review));
    }

    @PostMapping("/course/{courseId}/user/{userId}")
    public ResponseEntity<CourseReview> createReviewForCourse(@PathVariable Long courseId, @PathVariable Long userId, @RequestParam int rating, @RequestParam String comment) {
        return ResponseEntity.ok(service.createReview(courseId, userId, rating, comment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseReview> getReview(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CourseReview> getAllReviews() {
        return service.findAll();
    }

    @GetMapping("/course/{courseId}")
    public List<CourseReview> getReviewsByCourse(@PathVariable Long courseId) {
        return service.findByCourseId(courseId);
    }

    @GetMapping("/user/{userId}")
    public List<CourseReview> getReviewsByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseReview> updateReview(@PathVariable Long id, @RequestBody CourseReview review) {
        review.setId(id);
        return ResponseEntity.ok(service.save(review));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}