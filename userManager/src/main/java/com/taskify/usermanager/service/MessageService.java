package com.taskify.usermanager.service;

import com.taskify.usermanager.data.request.UserRequest;

import java.util.UUID;

public interface MessageService {
    UUID getOrganizationIdByToken(String token);
    void sendUser(UserRequest userRequest);
}
