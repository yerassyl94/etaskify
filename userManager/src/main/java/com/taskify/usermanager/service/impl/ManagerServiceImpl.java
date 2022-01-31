package com.taskify.usermanager.service.impl;

import com.taskify.usermanager.data.request.NewUserRequest;
import com.taskify.usermanager.data.request.UserRequest;
import com.taskify.usermanager.service.ManagerService;
import com.taskify.usermanager.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private MessageService messageService;

    @Override
    public void createUser(NewUserRequest newUserRequest, UUID organizationId) {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(newUserRequest.getEmail());
        userRequest.setName(newUserRequest.getName());
        userRequest.setSurname(newUserRequest.getSurname());
        userRequest.setOrganizationId(organizationId);
        messageService.sendUser(userRequest);
    }
}
