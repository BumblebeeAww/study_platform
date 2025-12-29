package com.studyplatform.controller;

import com.studyplatform.entity.Lesson;
import com.studyplatform.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService service;

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        return ResponseEntity.ok(service.save(lesson));
    }

    @PostMapping("/module/{moduleId}")
    public ResponseEntity<Lesson> createLessonForModule(@PathVariable Long moduleId, @RequestParam String title, @RequestParam String content) {
        return ResponseEntity.ok(service.createLessonForModule(moduleId, title, content));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Lesson> getAllLessons() {
        return service.findAll();
    }

    @GetMapping("/module/{moduleId}")
    public List<Lesson> getLessonsByModule(@PathVariable Long moduleId) {
        return service.findByModuleId(moduleId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        lesson.setId(id);
        return ResponseEntity.ok(service.save(lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}