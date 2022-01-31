package com.taskify.authorization.data.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private UUID organizationId;
}
