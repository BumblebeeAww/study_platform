package com.studyplatform.service;

import com.studyplatform.entity.Course;
import com.studyplatform.entity.Enrollment;
import com.studyplatform.entity.User;
import com.studyplatform.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository repo;
    private final UserService userService;
    private final CourseService courseService;

    public Enrollment save(Enrollment enrollment) { return repo.save(enrollment); }
    public Optional<Enrollment> findById(Long id) { return repo.findById(id); }
    public List<Enrollment> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public Enrollment enrollUserInCourse(Long userId, Long courseId) {
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseService.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Enrollment enrollment = new Enrollment(null, LocalDateTime.now(), user, course);
        return repo.save(enrollment);
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public List<Enrollment> findByCourseId(Long courseId) {
        return repo.findByCourseId(courseId);
    }
}