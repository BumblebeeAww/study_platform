package com.studyplatform.repository;

import com.studyplatform.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
    Optional<Tag> findByName(String name);
}