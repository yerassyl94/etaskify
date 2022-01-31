package com.taskify.organization.data.request;

import lombok.Data;

@Data
public class OrganizationRequestBody {
    private String name;
    private String phone;
    private String address;
    private String adminUsername;
    private String adminEmail;
    private String adminPassword;
}
