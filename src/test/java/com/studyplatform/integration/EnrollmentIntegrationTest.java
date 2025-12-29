package com.studyplatform.integration;

import com.studyplatform.entity.*;
import com.studyplatform.enums.Role;
import com.studyplatform.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EnrollmentIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testEnrollStudentInCourse() {
        // Создать категорию
        Category category = categoryService.save(Category.builder().name("General").build());

        // Создать преподавателя и курс
        User teacher = userService.save(User.builder().name("Teacher5").email("t5@test.com").password("pass").role(Role.TEACHER).build());
        Course course = courseService.save(Course.builder()
                .title("Course5")
                .description("Desc")
                .instructor(teacher)
                .category(category)
                .build());

        // Создать студента и записать его на курс
        User student = userService.save(User.builder().name("Student2").email("s2@test.com").password("pass").role(Role.STUDENT).build());
        Enrollment enrollment = enrollmentService.enrollUserInCourse(student.getId(), course.getId());
        Assertions.assertNotNull(enrollment.getId());
        Assertions.assertEquals(student.getId(), enrollment.getStudent().getId());
        Assertions.assertEquals(course.getId(), enrollment.getCourse().getId());
    }
}