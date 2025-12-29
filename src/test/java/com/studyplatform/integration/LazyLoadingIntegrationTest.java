package com.studyplatform.integration;

import com.studyplatform.entity.Course;
import com.studyplatform.service.CourseService;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class LazyLoadingIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void testLazyLoadingException() {
        Optional<Course> courseOpt = courseService.findById(1L);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            Assertions.assertThrows(LazyInitializationException.class, () -> course.getModules().size());
        } else {
            Assertions.fail("No course found for lazy loading test");
        }
    }
}