package com.studyplatform.integration;

import com.studyplatform.entity.User;
import com.studyplatform.enums.Role;
import com.studyplatform.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateAndRetrieveUser() {
        // Создать пользователя
        User user = User.builder().name("Test User").email("user@test.com").password("password").role(Role.STUDENT).build();
        User saved = userService.save(user);
        Assertions.assertNotNull(saved.getId());

        // Проверить чтение
        Optional<User> found = userService.findById(saved.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals("Test User", found.get().getName());

        // Проверить обновление
        saved.setName("Updated User");
        userService.save(saved);
        found = userService.findById(saved.getId());
        Assertions.assertEquals("Updated User", found.get().getName());

        // Проверить удаление
        userService.delete(saved.getId());
        Assertions.assertFalse(userService.findById(saved.getId()).isPresent());
    }
}