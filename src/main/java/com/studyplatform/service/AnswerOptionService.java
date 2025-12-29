package com.studyplatform.service;

import com.studyplatform.entity.AnswerOption;
import com.studyplatform.repository.AnswerOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerOptionService {
    private final AnswerOptionRepository repo;

    public AnswerOption save(AnswerOption answerOption) { return repo.save(answerOption); }
    public Optional<AnswerOption> findById(Long id) { return repo.findById(id); }
    public List<AnswerOption> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public List<AnswerOption> findByQuestionId(Long questionId) {
        return repo.findByQuestionId(questionId);
    }
}