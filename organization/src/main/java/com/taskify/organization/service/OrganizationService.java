package com.taskify.organization.service;

import com.taskify.organization.data.request.OrganizationRequestBody;
import com.taskify.organization.entity.Admin;
import com.taskify.organization.entity.Organization;
import lombok.NonNull;

public interface OrganizationService {
    void create(@NonNull OrganizationRequestBody organizationRequestBody);
    Organization getOrganizationByAdminUsername(@NonNull String adminUsername);
}
