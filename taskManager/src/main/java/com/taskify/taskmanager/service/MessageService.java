package com.taskify.taskmanager.service;

import com.taskify.taskmanager.data.request.UserData;

import java.util.UUID;

public interface MessageService {
    UUID getUserIdByToken(String token);
    Boolean verifyUserSet(UserData userData);
}
