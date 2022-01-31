package com.taskify.organization.service.impl;

import com.taskify.organization.entity.Admin;
import com.taskify.organization.exception.AlreadyExistsException;
import com.taskify.organization.exception.BadRequestException;
import com.taskify.organization.repository.AdminRepository;
import com.taskify.organization.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin create(@NonNull String username, @NonNull String email,@NonNull String password) {
        checkUsername(username);
        checkEmail(email);
        checkPassword(password);

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password));

        return adminRepository.save(admin);
    }

    @Override
    public Admin getByUsername(@NonNull String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    public void checkUsername(@NonNull String username){
        Boolean isUsernameOccupied = adminRepository.existsByUsernameContainingIgnoreCase(username);
        if (isUsernameOccupied){
            throw new AlreadyExistsException(String.format("Admin with username: %s already exists", username));
        }
    }

    public void checkEmail(@NonNull String email){
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new BadRequestException("Invalid email pattern");
        }

        Boolean isEmailOccupied = adminRepository.existsByEmailContainingIgnoreCase(email);
        if (isEmailOccupied){
            throw new AlreadyExistsException(String.format("Admin with email: %s already exists", email));
        }
    }

    public void checkPassword(@NonNull String password){
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()){
            throw new BadRequestException("Invalid password pattern");
        }
    }
}
