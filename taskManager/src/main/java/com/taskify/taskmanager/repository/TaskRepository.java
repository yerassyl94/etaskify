package com.taskify.taskmanager.repository;

import com.taskify.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByOrganizationId(UUID organizationId);
}
