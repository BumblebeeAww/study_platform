package com.studyplatform;

import com.studyplatform.entity.*;
import com.studyplatform.entity.Module;
import com.studyplatform.enums.Role;
import com.studyplatform.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class StudyApp {

    private static final Logger logger = LoggerFactory.getLogger(StudyApp.class);

    public static void main(String[] args) {
        SpringApplication.run(StudyApp.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository,
            CourseRepository courseRepository,
            ModuleRepository moduleRepository,
            LessonRepository lessonRepository,
            AssignmentRepository assignmentRepository,
            QuizRepository quizRepository,
            QuestionRepository questionRepository,
            AnswerOptionRepository answerOptionRepository,
            EnrollmentRepository enrollmentRepository,
            SubmissionRepository submissionRepository,
            QuizSubmissionRepository quizSubmissionRepository,
            CourseReviewRepository courseReviewRepository,
            NotificationRepository notificationRepository
    ) {
        return args -> {
            logger.info("🚀 Начинаем инициализацию тестовых данных...");

            // Очистка всех таблиц (в порядке зависимостей, чтобы избежать ошибок FK)
            notificationRepository.deleteAll();
            courseReviewRepository.deleteAll();
            quizSubmissionRepository.deleteAll();
            submissionRepository.deleteAll();
            enrollmentRepository.deleteAll();
            answerOptionRepository.deleteAll();
            questionRepository.deleteAll();
            quizRepository.deleteAll();
            assignmentRepository.deleteAll();
            lessonRepository.deleteAll();
            moduleRepository.deleteAll();
            courseRepository.deleteAll();
            tagRepository.deleteAll();
            categoryRepository.deleteAll();
            userRepository.deleteAll();

            // Создание пользователей
            User teacher = User.builder()
                    .name("Иван Преподаватель")
                    .email("teacher@test.com")
                    .password("password123")
                    .role(Role.TEACHER)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(teacher);

            User student = User.builder()
                    .name("Петр Студент")
                    .email("student@test.com")
                    .password("password123")
                    .role(Role.STUDENT)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(student);

            User admin = User.builder()
                    .name("Администратор")
                    .email("admin@test.com")
                    .password("password123")
                    .role(Role.ADMIN)
                    .createdAt(LocalDateTime.now())
                    .build();
            userRepository.save(admin);

            logger.info("✅ Тестовые пользователи созданы: {}", userRepository.count());

            // Создание категорий
            Category programming = Category.builder()
                    .name("Программирование")
                    .description("Курсы по языкам программирования")
                    .build();
            categoryRepository.save(programming);

            Category design = Category.builder()
                    .name("Дизайн")
                    .description("Курсы по графическому дизайну")
                    .build();
            categoryRepository.save(design);

            logger.info("✅ Категории созданы: {}", categoryRepository.count());

            // Создание тегов
            Tag javaTag = Tag.builder()
                    .name("Java")
                    .courses(new java.util.ArrayList<>())
                    .build();
            tagRepository.save(javaTag);

            Tag springTag = Tag.builder()
                    .name("Spring")
                    .courses(new java.util.ArrayList<>())
                    .build();
            tagRepository.save(springTag);

            logger.info("✅ Теги созданы: {}", tagRepository.count());

            // Создание курсов
            Course javaCourse = Course.builder()
                    .title("Основы Java")
                    .description("Введение в Java")
                    .instructor(teacher)
                    .category(programming)
                    .tags(Arrays.asList(javaTag, springTag))
                    .createdAt(LocalDateTime.now())
                    .modules(new java.util.ArrayList<>())
                    .build();
            courseRepository.save(javaCourse);

            Course designCourse = Course.builder()
                    .title("Основы Дизайна")
                    .description("Введение в дизайн")
                    .instructor(teacher)
                    .category(design)
                    .tags(new java.util.ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .modules(new java.util.ArrayList<>())
                    .build();
            courseRepository.save(designCourse);

            logger.info("✅ Курсы созданы: {}", courseRepository.count());

            // Создание модулей
            Module javaModule1 = Module.builder()
                    .title("Введение в Java")
                    .description("Базовые концепции")
                    .orderIndex(1)
                    .course(javaCourse)
                    .lessons(new java.util.ArrayList<>())
                    .quizzes(new java.util.ArrayList<>())
                    .build();
            moduleRepository.save(javaModule1);

            Module designModule1 = Module.builder()
                    .title("Цвета и формы")
                    .description("Основы дизайна")
                    .orderIndex(1)
                    .course(designCourse)
                    .lessons(new java.util.ArrayList<>())
                    .quizzes(new java.util.ArrayList<>())
                    .build();
            moduleRepository.save(designModule1);

            logger.info("✅ Модули созданы: {}", moduleRepository.count());

            // Создание уроков
            Lesson javaLesson1 = Lesson.builder()
                    .title("Переменные и типы")
                    .content("Объяснение переменных")
                    .videoUrl("Видео: https://example.com")
                    .orderIndex(1)
                    .module(javaModule1)
                    .build();
            lessonRepository.save(javaLesson1);

            logger.info("✅ Уроки созданы: {}", lessonRepository.count());

            // Создание заданий
            Assignment javaAssignment1 = Assignment.builder()
                    .title("Написать Hello World")
                    .description("Создайте простую программу")
                    .dueDate(LocalDateTime.now().plusDays(7))
                    .module(javaModule1)
                    .user(teacher)
                    .build();
            assignmentRepository.save(javaAssignment1);

            logger.info("✅ Задания созданы: {}", assignmentRepository.count());

            // Создание тестов (Quiz)
            Quiz javaQuiz1 = Quiz.builder()
                    .title("Тест по переменным")
                    .description("Проверьте знания")
                    .maxScore(10)
                    .module(javaModule1)
                    .questions(new java.util.ArrayList<>())
                    .user(teacher)
                    .build();
            quizRepository.save(javaQuiz1);

            // Вопросы и варианты ответов
            Question question1 = Question.builder()
                    .text("Что такое переменная?")
                    .type(com.studyplatform.enums.QuestionType.MULTIPLE_CHOICE)
                    .quiz(javaQuiz1)
                    .options(new java.util.ArrayList<>())
                    .build();
            questionRepository.save(question1);

            AnswerOption option1 = AnswerOption.builder()
                    .optionText("Место для хранения данных")
                    .isCorrect(true)
                    .question(question1)
                    .build();
            answerOptionRepository.save(option1);

            AnswerOption option2 = AnswerOption.builder()
                    .optionText("Функция")
                    .isCorrect(false)
                    .question(question1)
                    .build();
            answerOptionRepository.save(option2);

            logger.info("✅ Тесты созданы: {}", quizRepository.count());

            // Запись студента на курс
            Enrollment enrollment = Enrollment.builder()
                    .enrolledAt(LocalDateTime.now())
                    .student(student)
                    .course(javaCourse)
                    .build();
            enrollmentRepository.save(enrollment);

            logger.info("✅ Записи на курсы созданы: {}", enrollmentRepository.count());

            // Сдача задания (пример)
            Submission submission = Submission.builder()
                    .content("Мой код: public class Hello { public static void main(String[] args) { System.out.println(\"Hello World\"); } }")
                    .submittedAt(LocalDateTime.now())
                    .assignment(javaAssignment1)
                    .student(student)
                    .build();
            submissionRepository.save(submission);

            logger.info("✅ Сдачи заданий созданы: {}", submissionRepository.count());

            // Прохождение теста (пример)
            QuizSubmission quizSubmission = QuizSubmission.builder()
                    .score(8)  // Примерная оценка
                    .submittedAt(LocalDateTime.now())
                    .quiz(javaQuiz1)
                    .student(student)
                    .build();
            quizSubmissionRepository.save(quizSubmission);

            logger.info("✅ Прохождения тестов созданы: {}", quizSubmissionRepository.count());

            // Отзыв о курсе (пример)
            CourseReview review = CourseReview.builder()
                    .rating(5)
                    .comment("Отличный курс!")
                    .createdAt(LocalDateTime.now())
                    .reviewer(student)
                    .course(javaCourse)
                    .build();
            courseReviewRepository.save(review);

            logger.info("✅ Отзывы созданы: {}", courseReviewRepository.count());

            // Уведомление (пример)
            Notification notification = Notification.builder()
                    .message("Ваш курс 'Основы Java' обновлен!")
                    .isRead(false)
                    .createdAt(LocalDateTime.now())
                    .type(com.studyplatform.enums.NotificationType.COURSE_UPDATE)
                    .user(student)
                    .build();
            notificationRepository.save(notification);

            logger.info("✅ Уведомления созданы: {}", notificationRepository.count());

            logger.info("🎉 Инициализация завершена! Все тестовые данные созданы.");
        };
    }
}