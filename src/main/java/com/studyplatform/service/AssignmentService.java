package com.studyplatform.service;

import com.studyplatform.entity.Assignment;
import com.studyplatform.entity.Submission;
import com.studyplatform.entity.User;
import com.studyplatform.repository.AssignmentRepository;
import com.studyplatform.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository repo;
    private final SubmissionRepository submissionRepo;
    private final UserService userService;

    public Assignment save(Assignment assignment) { return repo.save(assignment); }
    public Optional<Assignment> findById(Long id) { return repo.findById(id); }
    public List<Assignment> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public Submission submitAssignment(Long assignmentId, Long userId, String content) {
        Assignment assignment = findById(assignmentId).orElseThrow(() -> new RuntimeException("Assignment not found"));
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Submission submission = Submission.builder()
                .content(content)
                .submittedAt(LocalDateTime.now())
                .assignment(assignment)
                .student(user)
                .build();
        return submissionRepo.save(submission);
    }

    public List<Submission> getSubmissionsForAssignment(Long assignmentId) {
        return submissionRepo.findByAssignmentId(assignmentId);
    }
}