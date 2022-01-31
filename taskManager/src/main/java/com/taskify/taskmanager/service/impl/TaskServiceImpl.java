package com.taskify.taskmanager.service.impl;

import com.taskify.taskmanager.data.request.TaskRequest;
import com.taskify.taskmanager.data.request.UserData;
import com.taskify.taskmanager.entity.Assignee;
import com.taskify.taskmanager.entity.Task;
import com.taskify.taskmanager.exception.BadRequestException;
import com.taskify.taskmanager.repository.AssigneeRepository;
import com.taskify.taskmanager.repository.TaskRepository;
import com.taskify.taskmanager.service.MessageService;
import com.taskify.taskmanager.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private MessageService messageService;
    private TaskRepository taskRepository;
    private AssigneeRepository assigneeRepository;

    @Override
    @Transactional
    public void create(@NonNull TaskRequest taskRequest, @NonNull UUID creatorId) {
        checkAssignees(taskRequest.getAssignees(), creatorId);

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setDeadline(OffsetDateTime.of(taskRequest.getDeadline(), LocalTime.now(), ZoneOffset.UTC));
        task.setCreatedBy(creatorId);
        task.setOrganizationId(taskRequest.getOrganizationId());

        Task savedTask = taskRepository.save(task);

        for (UUID assignees : taskRequest.getAssignees()) {
            Assignee assignee = new Assignee();
            assignee.setTask(savedTask);
            assignee.setId(assignees);
            assigneeRepository.save(assignee);
        }
    }

    @Override
    public List<Task> getOrganizationTasks(@NonNull UUID organizationId, @NonNull UUID userId) {
        return taskRepository.findAllByOrganizationId(organizationId);
    }

    void checkAssignees(Set<UUID> assignees, UUID creatorId) {
        if (assignees == null || assignees.isEmpty()) {
            throw new BadRequestException("Assignees are required");
        }

        UserData userData = new UserData();
        userData.setAssignees(assignees);
        userData.setCreatorId(creatorId);

        if (!messageService.verifyUserSet(userData)) {
            throw new BadRequestException("Assignees are not valid");
        }
    }
}
