package com.taskify.taskmanager.controller;

import com.taskify.taskmanager.data.request.TaskRequest;
import com.taskify.taskmanager.entity.Task;
import com.taskify.taskmanager.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping
    ResponseEntity<Void> create(@RequestAttribute UUID userId, @RequestBody TaskRequest taskRequest){
        taskService.create(taskRequest, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{organizationId}/tasks")
    ResponseEntity<List<Task>> getAllOrganizationTasks(@PathVariable UUID organizationId, @RequestAttribute UUID userId){
        return ResponseEntity.ok(taskService.getOrganizationTasks(organizationId, userId));
    }
}
