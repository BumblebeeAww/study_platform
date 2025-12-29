package com.studyplatform.integration;

import com.studyplatform.entity.*;
import com.studyplatform.entity.Module;
import com.studyplatform.enums.Role;
import com.studyplatform.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AssignmentIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testSubmitAssignment() {
        // Создать категорию
        Category category = categoryService.save(Category.builder().name("General").build());

        // Создать пользователя, курс и модуль
        User teacher = userService.save(User.builder().name("Teacher3").email("t3@test.com").password("pass").role(Role.TEACHER).build());
        Course course = courseService.save(Course.builder()
                .title("Course3")
                .description("Desc")
                .instructor(teacher)
                .category(category)
                .build());
        Module module = moduleService.save(Module.builder().title("Test Module").description("Desc").course(course).build());

        // Создать задание
        Assignment assignment = Assignment.builder()
                .title("Test Assignment")
                .description("Desc")
                .module(module)
                .dueDate(LocalDateTime.now().plusDays(7))
                .user(teacher)
                .build();
        Assignment savedAssignment = assignmentService.save(assignment);
        Assertions.assertNotNull(savedAssignment.getId());

        // Создать студента и отправить задание
        User student = userService.save(User.builder().name("Student").email("s@test.com").password("pass").role(Role.STUDENT).build());
        Submission submission = submissionService.submitAssignment(student.getId(), savedAssignment.getId(), "My submission content");
        Assertions.assertNotNull(submission.getId());
        Assertions.assertEquals("My submission content", submission.getContent());
    }

    @Test
    public void testCascadeSaveAssignments() {
        // Создать категорию
        Category category = categoryService.save(Category.builder().name("General").build());

        // Создать модуль с заданиями (проверить каскад)
        User teacher = userService.save(User.builder().name("Teacher4").email("t4@test.com").password("pass").role(Role.TEACHER).build());
        Course course = courseService.save(Course.builder()
                .title("Course4")
                .description("Desc")
                .instructor(teacher)
                .category(category)
                .build());
        Module module = Module.builder().title("Module with Assignments").description("Desc").course(course).build();
        Module saved = moduleService.save(module);
        Assertions.assertNotNull(saved.getId());
    }
}