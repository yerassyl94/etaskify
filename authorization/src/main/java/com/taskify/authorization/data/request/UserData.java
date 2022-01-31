package com.taskify.authorization.data.request;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserData {
    private UUID creatorId;
    private Set<UUID> assignees;
}
