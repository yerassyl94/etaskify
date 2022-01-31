package com.taskify.organization.service;

import com.taskify.organization.data.request.AuthRequest;
import com.taskify.organization.data.response.LoginResponse;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    LoginResponse authenticate(@NonNull AuthRequest authRequest);

}
