package com.studyplatform.service;

import com.studyplatform.entity.Course;
import com.studyplatform.entity.CourseReview;
import com.studyplatform.entity.User;
import com.studyplatform.repository.CourseReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseReviewService {
    private final CourseReviewRepository repo;
    private final UserService userService;
    private final CourseService courseService;

    public CourseReview save(CourseReview review) { return repo.save(review); }
    public Optional<CourseReview> findById(Long id) { return repo.findById(id); }
    public List<CourseReview> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public CourseReview createReview(Long userId, Long courseId, int rating, String comment) {
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseService.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        CourseReview review = new CourseReview(null, rating, comment, LocalDateTime.now(), course, user);
        return repo.save(review);
    }

    public List<CourseReview> findByCourseId(Long courseId) {
        return repo.findByCourseId(courseId);
    }

    public List<CourseReview> findByUserId(Long userId) {
        return repo.findByReviewerId(userId);
    }
}