package com.studyplatform.repository;

import com.studyplatform.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("SELECT m FROM Module m LEFT JOIN FETCH m.course WHERE m.course.id = :courseId")
    List<Module> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT m FROM Module m LEFT JOIN FETCH m.course WHERE m.id = :id")
    Optional<Module> findByIdWithCourse(@Param("id") Long id);

    @Query("SELECT m FROM Module m LEFT JOIN FETCH m.course LEFT JOIN FETCH m.lessons LEFT JOIN FETCH m.quizzes WHERE m.id = :id")
    Optional<Module> findByIdWithRelations(@Param("id") Long id);

    @Query("SELECT m FROM Module m LEFT JOIN FETCH m.course LEFT JOIN FETCH m.lessons LEFT JOIN FETCH m.quizzes WHERE m.course.id = :courseId")
    List<Module> findByCourseIdWithRelations(@Param("courseId") Long courseId);

    boolean existsByTitleAndCourseId(String title, Long courseId);

    @Query("SELECT COALESCE(MAX(m.orderIndex), -1) FROM Module m WHERE m.course.id = :courseId")
    int findMaxOrderIndexByCourseId(@Param("courseId") Long courseId);
}