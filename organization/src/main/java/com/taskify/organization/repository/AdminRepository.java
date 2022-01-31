package com.taskify.organization.repository;

import com.taskify.organization.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Boolean existsByUsernameContainingIgnoreCase(String username);
    Boolean existsByEmailContainingIgnoreCase(String email);

    Optional<Admin> findByUsername(String username);
}
