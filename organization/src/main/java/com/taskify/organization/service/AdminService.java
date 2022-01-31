package com.taskify.organization.service;

import com.taskify.organization.data.request.AuthRequest;
import com.taskify.organization.data.response.LoginResponse;
import com.taskify.organization.entity.Admin;
import lombok.NonNull;

public interface AdminService {
    Admin create(@NonNull String username,@NonNull String email, @NonNull String password);
    Admin getByUsername(@NonNull String username);
    void checkUsername(@NonNull String username);
    void checkEmail(@NonNull String email);
}
