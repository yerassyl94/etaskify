package com.taskify.taskmanager.repository;

import com.taskify.taskmanager.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssigneeRepository extends JpaRepository<Assignee, UUID>  {
}
