package com.studyplatform.entity;

import com.studyplatform.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @Setter
    private boolean isRead;

    @NotNull(message = "Created at cannot be null")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type cannot be null")
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User cannot be null")
    private User user;
}