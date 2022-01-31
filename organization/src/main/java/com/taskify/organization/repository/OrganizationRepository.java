package com.taskify.organization.repository;

import com.taskify.organization.entity.Admin;
import com.taskify.organization.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Boolean existsByNameContainingIgnoreCase(String name);
    Optional<Organization> findByAdmin(Admin admin);
}
