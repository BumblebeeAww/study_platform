package com.studyplatform.service;

import com.studyplatform.entity.Question;
import com.studyplatform.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository repo;

    public Question save(Question question) { return repo.save(question); }
    public Optional<Question> findById(Long id) { return repo.findById(id); }
    public List<Question> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public List<Question> findByQuizId(Long quizId) {
        return repo.findByQuizId(quizId);
    }
}