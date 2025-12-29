## Учебная Платформа (Study Platform)

Это Spring Boot приложение для управления учебной платформой, включающее курсы, модули, уроки, задания, тесты и отзывы. Приложение использует JPA/Hibernate для работы с PostgreSQL, поддерживает REST API и интеграционные тесты.

## Технологии
- Java 17+
- Spring Boot (Data JPA, Web, Validation)
- Hibernate (JPA)
- PostgreSQL
- Maven
- Docker & Docker Compose
- GitHub Actions (CI/CD)

## Структура проекта
- `controller/` — REST контроллеры для API.
- `dto/` — DTO для API (например, CourseDTO).
- `entity/` — JPA сущности (17 сущностей: User, Course, Module, Lesson, Assignment, Submission, Quiz, Question, AnswerOption, QuizSubmission, Enrollment, Category, Tag, CourseReview, Notification, Profile, Progress).
- `enums/` — Перечисления (Role, QuestionType, NotificationType, AssignmentType).
- `repository/` — Репозитории Spring Data JPA с кастомными запросами.
- `service/` — Бизнес-логика и транзакции.
- `integration/` — Интеграционные тесты.
- `StudyApp.java` — Главный класс приложения с инициализацией демо-данных.

## Запуск приложения

### Локально (с PostgreSQL)
1. Установите Java 17+ и Maven.
2. Установите PostgreSQL и создайте БД `studyplatform`.
3. В `src/main/resources/application.properties` настройте подключение:
   spring.datasource.url=jdbc:postgresql://localhost:5432/studyplatform
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
4. Запустите: `mvn spring-boot:run`.
5. Приложение запустится на `http://localhost:8080`.

### С Docker Compose
1. Установите Docker и Docker Compose.
2. Запустите: `docker-compose up --build`.
3. Приложение будет доступно на `http://localhost:8080`, PostgreSQL на порту 5432.

## API Endpoints
- **Пользователи**: `GET /api/users`, `POST /api/users/register`
- **Курсы**: `GET /api/courses`, `POST /api/courses`, `GET /api/courses/{id}`
- **Модули**: `GET /api/modules`, `POST /api/modules`
- **Уроки**: `GET /api/lessons`, `POST /api/lessons`
- **Задания**: `POST /api/assignments/{id}/submit`
- **Тесты**: `POST /api/quizzes/{id}/submit`
- **Записи на курс**: `POST /api/enrollments`, `GET /api/enrollments/user/{userId}`
- **Отзывы**: `POST /api/reviews`, `GET /api/reviews/course/{courseId}`
- **Категории/Теги/Уведомления**: Аналогично выше.

Полную документацию API можно сгенерировать с помощью Swagger (добавьте зависимость `springfox-boot-starter` и аннотации).

## Тестирование
- Интеграционные тесты: `mvn test` (используют H2 в памяти для тестов, но можно настроить PostgreSQL в `application-test.properties`).
- Покрытие: CRUD, каскадное сохранение, lazy loading (включая тест на LazyInitializationException).
- Тесты запускаются автоматически в CI/CD.

## CI/CD
- GitHub Actions: Автоматический запуск тестов на push/PR, сборка образа Docker и push в Docker Hub.
- Проверка кода: Используется Spotless для форматирования Java кода.
