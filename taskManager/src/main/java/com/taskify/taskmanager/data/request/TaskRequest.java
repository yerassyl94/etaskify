package com.taskify.taskmanager.data.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String status;
    private LocalDate deadline;
    private Set<UUID> assignees;
    private UUID organizationId;
}
