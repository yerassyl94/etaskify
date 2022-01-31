package com.taskify.authorization.repository;

import com.taskify.authorization.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Boolean existsByEmailContainingIgnoreCase(String email);
    Optional<User> findByEmailContainingIgnoreCase(String email);
}
