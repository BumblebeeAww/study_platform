package com.studyplatform.service;

import com.studyplatform.entity.User;
import com.studyplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    public User save(User user) { return repo.save(user); }
    public Optional<User> findById(Long id) { return repo.findById(id); }
    public List<User> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }

    public Optional<User> findByUsername(String username) {
        return repo.findByName(username);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}