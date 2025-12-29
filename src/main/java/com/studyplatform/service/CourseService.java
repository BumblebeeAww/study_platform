package com.studyplatform.service;

import com.studyplatform.dto.CourseDTO;
import com.studyplatform.dto.InstructorDTO;
import com.studyplatform.entity.Course;
import com.studyplatform.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository repo;

    public Course save(Course course) { return repo.save(course); }

    public Optional<Course> findById(Long id) { return repo.findById(id); }

    @Transactional(readOnly = true)
    public List<CourseDTO> findAllCourses() {
        return repo.findAllWithInstructor().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CourseDTO> findCourseDTOById(Long id) {
        return repo.findByIdWithInstructor(id).map(this::mapToDTO);
    }

    public void delete(Long id) { repo.deleteById(id); }

    public List<Course> findByTitle(String title) {
        return repo.findByTitleContaining(title);
    }

    private CourseDTO mapToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        if (course.getInstructor() != null) {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setId(course.getInstructor().getId());
            instructorDTO.setName(course.getInstructor().getName());
            instructorDTO.setEmail(course.getInstructor().getEmail());
            dto.setInstructor(instructorDTO);
        }
        return dto;
    }
}