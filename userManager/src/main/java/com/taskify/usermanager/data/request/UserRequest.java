package com.taskify.usermanager.data.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequest extends NewUserRequest {
    private UUID organizationId;
}
