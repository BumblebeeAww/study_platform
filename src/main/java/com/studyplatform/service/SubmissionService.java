package com.studyplatform.service;

import com.studyplatform.entity.Assignment;
import com.studyplatform.entity.Submission;
import com.studyplatform.entity.User;
import com.studyplatform.repository.AssignmentRepository;
import com.studyplatform.repository.SubmissionRepository;
import com.studyplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;

    public Submission submitAssignment(Long studentId, Long assignmentId, String content) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        Submission submission = new Submission();
        submission.setContent(content);
        submission.setSubmittedAt(LocalDateTime.now());
        submission.setAssignment(assignment);
        submission.setStudent(student);

        return submissionRepository.save(submission);
    }
}