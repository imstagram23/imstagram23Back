package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposiotry extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
