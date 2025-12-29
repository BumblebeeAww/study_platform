package com.studyplatform.service;

import com.studyplatform.entity.Category;
import com.studyplatform.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public Category save(Category category) { return repo.save(category); }
    public Optional<Category> findById(Long id) { return repo.findById(id); }
    public List<Category> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public Optional<Category> findByName(String name) {
        return repo.findByName(name);
    }
}