package com.studyplatform.repository;

import com.studyplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTitleContaining(String title);

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.instructor")
    List<Course> findAllWithInstructor();

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.instructor WHERE c.id = :id")
    Optional<Course> findByIdWithInstructor(@Param("id") Long id);

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.instructor LEFT JOIN FETCH c.category LEFT JOIN FETCH c.tags WHERE c.id = :id")
    Optional<Course> findByIdWithInstructorCategoryAndTags(@Param("id") Long id);

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.instructor LEFT JOIN FETCH c.category LEFT JOIN FETCH c.tags")
    List<Course> findAllWithInstructorCategoryAndTags();
}