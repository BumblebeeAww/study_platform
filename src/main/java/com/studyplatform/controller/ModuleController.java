package com.studyplatform.controller;

import com.studyplatform.entity.Module;
import com.studyplatform.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService service;

    @PostMapping
    public ResponseEntity<Module> createModule(@RequestBody Module module) {
        return ResponseEntity.ok(service.save(module));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Module> createModuleForCourse(
            @PathVariable Long courseId,
            @RequestParam String title,
            @RequestParam(required = false) String description) {
        return ResponseEntity.ok(service.createModuleForCourse(courseId, title, description));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Module> getModule(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Module> getAllModules() {
        return service.findAll();
    }

    @GetMapping("/course/{courseId}")
    public List<Module> getModulesByCourse(@PathVariable Long courseId) {
        return service.findByCourseId(courseId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable Long id, @RequestBody Module module) {
        module.setId(id);
        return ResponseEntity.ok(service.save(module));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}