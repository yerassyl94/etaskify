package com.taskify.authorization.service;

import com.taskify.authorization.data.request.LoginRequest;
import com.taskify.authorization.data.request.UserData;
import com.taskify.authorization.data.request.UserRequest;
import com.taskify.authorization.data.response.LoginResponse;
import com.taskify.authorization.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void create(UserRequest userRequest);
    User getByEmail(String email);
    Boolean verifyOrganizationUsers(UserData userData);
    LoginResponse login(LoginRequest loginRequest);
}
