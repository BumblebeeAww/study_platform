package com.studyplatform.service;

import com.studyplatform.entity.Course;
import com.studyplatform.entity.Module;
import com.studyplatform.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository repo;
    private final CourseService courseService;

    public Module save(Module module) {
        return repo.save(module);
    }

    @Transactional(readOnly = true)
    public Optional<Module> findById(Long id) {
        return repo.findByIdWithCourse(id);
    }

    @Transactional(readOnly = true)
    public List<Module> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public Module createModuleForCourse(Long courseId, String title, String description) {
        Course course = courseService.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + courseId));

        int nextOrderIndex = repo.findMaxOrderIndexByCourseId(courseId) + 1;

        Module module = Module.builder()
                .title(title)
                .description(description)
                .orderIndex(nextOrderIndex)
                .course(course)
                .build();
        return repo.save(module);
    }

    @Transactional(readOnly = true)
    public List<Module> findByCourseId(Long courseId) {
        return repo.findByCourseId(courseId);
    }

    @Transactional
    public void updateOrderIndex(Long moduleId, int newOrderIndex) {
        Optional<Module> optionalModule = repo.findById(moduleId);
        if (optionalModule.isPresent()) {
            Module module = optionalModule.get();
            module.setOrderIndex(newOrderIndex);
            repo.save(module);
        }
    }
}