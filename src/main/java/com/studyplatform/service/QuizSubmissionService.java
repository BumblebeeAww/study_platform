package com.studyplatform.service;

import com.studyplatform.entity.QuizSubmission;
import com.studyplatform.repository.QuizSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizSubmissionService {
    private final QuizSubmissionRepository repo;

    public QuizSubmission save(QuizSubmission quizSubmission) { return repo.save(quizSubmission); }
    public Optional<QuizSubmission> findById(Long id) { return repo.findById(id); }
    public List<QuizSubmission> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public List<QuizSubmission> findByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public Optional<QuizSubmission> findByQuizIdAndStudentId(Long quizId, Long studentId) {
        return repo.findByQuizIdAndStudentId(quizId, studentId);
    }

    public List<QuizSubmission> findByQuizId(Long quizId) {
        return repo.findByQuizId(quizId);
    }
}