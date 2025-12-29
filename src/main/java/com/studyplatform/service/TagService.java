package com.studyplatform.service;

import com.studyplatform.entity.Tag;
import com.studyplatform.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagRepository repo;

    public Tag save(Tag tag) { return repo.save(tag); }
    public Optional<Tag> findById(Long id) { return repo.findById(id); }
    public List<Tag> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public Optional<Tag> findByName(String name) {
        return repo.findByName(name);
    }
}