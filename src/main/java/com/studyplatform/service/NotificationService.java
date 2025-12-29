package com.studyplatform.service;

import com.studyplatform.entity.Notification;
import com.studyplatform.entity.User;
import com.studyplatform.enums.NotificationType;
import com.studyplatform.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repo;
    private final UserService userService;

    public Notification save(Notification notification) { return repo.save(notification); }
    public Optional<Notification> findById(Long id) { return repo.findById(id); }
    public List<Notification> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public Notification createNotification(Long userId, String message, NotificationType type) {
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = Notification.builder()
                .message(message)
                .createdAt(LocalDateTime.now())
                .isRead(false)
                .type(type)
                .user(user)
                .build();
        return repo.save(notification);
    }

    public List<Notification> findByUserId(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Notification> findUnreadByUserId(Long userId) {
        return repo.findByUserIdAndIsReadFalse(userId);
    }

    public Notification markAsRead(Long id) {
        Notification notification = repo.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        return repo.save(notification);
    }
}