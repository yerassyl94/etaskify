package com.taskify.organization.service;

import java.util.UUID;

public interface MessageService {
    UUID getOrganizationIdFromReceivedToken(String token);
}
