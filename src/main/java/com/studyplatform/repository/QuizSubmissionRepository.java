package com.studyplatform.repository;

import com.studyplatform.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {
    List<QuizSubmission> findByStudentId(Long studentId);
    Optional<QuizSubmission> findByQuizIdAndStudentId(Long quizId, Long studentId);
    List<QuizSubmission> findByQuizId(Long quizId);
}
