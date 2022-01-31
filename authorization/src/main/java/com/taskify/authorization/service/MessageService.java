package com.taskify.authorization.service;

import com.taskify.authorization.data.request.UserData;
import com.taskify.authorization.data.request.UserRequest;

import java.util.Map;
import java.util.UUID;

public interface MessageService {
    UUID getUserIdFromReceivedToken(Map<String, String> tokenMap);
    Boolean verifyOrganizationUserList(UserData userData);
    void receiveNewUser(UserRequest userRequest);
}
