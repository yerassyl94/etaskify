package com.taskify.usermanager.data.request;

import lombok.Data;

@Data
public class NewUserRequest {
    private String name;
    private String surname;
    private String email;
}
