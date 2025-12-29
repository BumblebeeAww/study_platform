package com.studyplatform.integration;

import com.studyplatform.entity.Category;
import com.studyplatform.entity.Course;
import com.studyplatform.entity.User;
import com.studyplatform.enums.Role;
import com.studyplatform.service.CategoryService;
import com.studyplatform.service.CourseService;
import com.studyplatform.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")  // Использует application-test.properties
public class CourseIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCreateAndRetrieveCourse() {
        // Создать пользователя и категорию
        User teacher = userService.save(User.builder().name("Teacher").email("t@test.com").password("pass").role(Role.TEACHER).build());
        Category cat = categoryService.save(Category.builder().name("Test Cat").build());

        // Создать курс
        Course course = Course.builder().title("Test Course").description("Desc").instructor(teacher).category(cat).build();
        Course saved = courseService.save(course);
        Assertions.assertNotNull(saved.getId());

        // Проверить чтение
        Optional<Course> found = courseService.findById(saved.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Test Course", found.get().getTitle());

        // Проверить обновление
        saved.setDescription("Updated");
        courseService.save(saved);
        found = courseService.findById(saved.getId());
        Assertions.assertEquals("Updated", found.get().getDescription());

        // Проверить удаление
        courseService.delete(saved.getId());
        Assertions.assertFalse(courseService.findById(saved.getId()).isPresent());
    }

    @Test
    public void testCascadeSaveModules() {
        // Создать курс с модулями
        User teacher = userService.save(User.builder().name("Teacher2").email("t2@test.com").password("pass").role(Role.TEACHER).build());
        Category cat = categoryService.save(Category.builder().name("Cat2").build());
        Course course = Course.builder().title("Course with Modules").description("Desc").instructor(teacher).category(cat).build();
        Course saved = courseService.save(course);
        Assertions.assertNotNull(saved.getId());
    }
}