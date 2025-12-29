package com.studyplatform.repository;

import com.studyplatform.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByModuleId(Long moduleId);
    List<Assignment> findByModuleIdOrderByDueDate(Long moduleId);
}
