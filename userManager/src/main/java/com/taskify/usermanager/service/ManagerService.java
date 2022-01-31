package com.taskify.usermanager.service;

import com.taskify.usermanager.data.request.NewUserRequest;

import java.util.UUID;

public interface ManagerService {
    void createUser(NewUserRequest newUserRequest, UUID organizationId);
}
