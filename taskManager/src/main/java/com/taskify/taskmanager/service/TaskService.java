package com.taskify.taskmanager.service;

import com.taskify.taskmanager.data.request.TaskRequest;
import com.taskify.taskmanager.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    void create(TaskRequest taskRequest, UUID creatorId);
    List<Task> getOrganizationTasks(UUID organizationId, UUID userId);
}
