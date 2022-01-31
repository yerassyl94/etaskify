package com.taskify.usermanager.controller;

import com.taskify.usermanager.data.request.NewUserRequest;
import com.taskify.usermanager.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/manager")
@AllArgsConstructor
public class ManagerController {

    private ManagerService managerService;

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(
            @RequestAttribute UUID organizationId,
            @RequestBody NewUserRequest newUserRequest
    ) {
        managerService.createUser(newUserRequest, organizationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
