package com.taskify.organization.service;

import com.taskify.organization.exception.AlreadyExistsException;
import com.taskify.organization.exception.BadRequestException;
import com.taskify.organization.repository.AdminRepository;
import com.taskify.organization.service.impl.AdminServiceImpl;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTests {
    @Mock
    private AdminRepository adminRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService = new AdminServiceImpl(adminRepository, passwordEncoder);

    @Test
    public void existingUsernameShouldThrowException() {
        when(adminRepository.existsByUsernameContainingIgnoreCase("admin")).thenReturn(true);
        Throwable exception = assertThrows(AlreadyExistsException.class, () -> adminService.checkUsername("admin"));
        assertEquals("Admin with username: admin already exists", exception.getMessage());
    }

    @Test
    public void badEmailPatternShouldThrowException() {
        Throwable exception = assertThrows(BadRequestException.class, () -> adminService.checkEmail("admin"));
        assertEquals("Invalid email pattern", exception.getMessage());
    }

}
