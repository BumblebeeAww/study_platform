package com.studyplatform.service;

import com.studyplatform.entity.*;
import com.studyplatform.repository.AnswerOptionRepository;
import com.studyplatform.repository.QuestionRepository;
import com.studyplatform.repository.QuizRepository;
import com.studyplatform.repository.QuizSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository repo;
    private final QuestionRepository questionRepo;
    private final AnswerOptionRepository answerRepo;
    private final QuizSubmissionRepository submissionRepo;
    private final UserService userService;

    public Quiz save(Quiz quiz) { return repo.save(quiz); }
    public Optional<Quiz> findById(Long id) { return repo.findById(id); }
    public List<Quiz> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public QuizSubmission submitQuiz(Long quizId, Long userId, Map<Long, List<String>> answers) {
        Quiz quiz = findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        int score = calculateScore(quiz, answers);
        QuizSubmission submission = QuizSubmission.builder()
                .score(score)
                .submittedAt(LocalDateTime.now())
                .quiz(quiz)
                .student(user)
                .build();
        return submissionRepo.save(submission);
    }

    private int calculateScore(Quiz quiz, Map<Long, List<String>> answers) {
        int total = 0;
        for (Question q : quiz.getQuestions()) {
            List<String> userAnswers = answers.get(q.getId());
            if (userAnswers != null) {
                boolean correct = q.getOptions().stream().anyMatch(o -> o.getIsCorrect() && userAnswers.contains(o.getOptionText()));
                if (correct) total++;
            }
        }
        return total;
    }

    public List<QuizSubmission> getSubmissionsForQuiz(Long quizId) {
        return submissionRepo.findByQuizId(quizId);
    }
}