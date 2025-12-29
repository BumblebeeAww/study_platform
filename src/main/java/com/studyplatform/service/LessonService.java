package com.studyplatform.service;

import com.studyplatform.entity.Lesson;
import com.studyplatform.entity.Module;
import com.studyplatform.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository repo;
    private final ModuleService moduleService;

    public Lesson save(Lesson lesson) {
        return repo.save(lesson);
    }

    public Optional<Lesson> findById(Long id) {
        return repo.findById(id);
    }

    public List<Lesson> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Lesson createLessonForModule(Long moduleId, String title, String content) {
        Module module = moduleService.findById(moduleId).orElseThrow(() -> new RuntimeException("Module not found"));
        Lesson lesson = Lesson.builder()
                .title(title)
                .content(content)
                .videoUrl(null)
                .orderIndex(0)
                .module(module)
                .build();
        return repo.save(lesson);
    }

    public List<Lesson> findByModuleId(Long moduleId) {
        return repo.findByModuleId(moduleId);
    }
}