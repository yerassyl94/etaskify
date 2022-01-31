package com.taskify.organization.controller;

import com.taskify.organization.data.request.AuthRequest;
import com.taskify.organization.data.response.LoginResponse;
import com.taskify.organization.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private UserService userService;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }
}
