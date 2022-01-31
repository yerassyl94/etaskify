package com.taskify.organization.data.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
