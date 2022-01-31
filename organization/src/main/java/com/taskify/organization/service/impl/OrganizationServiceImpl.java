package com.taskify.organization.service.impl;

import com.taskify.organization.data.request.OrganizationRequestBody;
import com.taskify.organization.entity.Admin;
import com.taskify.organization.entity.Organization;
import com.taskify.organization.exception.AlreadyExistsException;
import com.taskify.organization.repository.OrganizationRepository;
import com.taskify.organization.service.AdminService;
import com.taskify.organization.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    private AdminService adminService;

    @Transactional
    @Override
    public void create(@NonNull OrganizationRequestBody organizationRequestBody) {
        checkOrganizationName(organizationRequestBody.getName());

        Admin admin = adminService.create(
                organizationRequestBody.getAdminUsername(),
                organizationRequestBody.getAdminEmail(),
                organizationRequestBody.getAdminPassword()
        );

        Organization organization = new Organization();
        organization.setName(organizationRequestBody.getName());
        organization.setPhone(organizationRequestBody.getPhone());
        organization.setAddress(organizationRequestBody.getPhone());
        organization.setAdmin(admin);

        organizationRepository.save(organization);
    }

    @Override
    public Organization getOrganizationByAdminUsername(@NonNull String adminUsername) {
        Admin admin = adminService.getByUsername(adminUsername);
        return organizationRepository.findByAdmin(admin).orElse(null);
    }

    private void checkOrganizationName(@NonNull String name) {
        if (organizationRepository.existsByNameContainingIgnoreCase(name)) {
            throw new AlreadyExistsException(String.format("Organization: %s already exists", name));
        }
    }
}
