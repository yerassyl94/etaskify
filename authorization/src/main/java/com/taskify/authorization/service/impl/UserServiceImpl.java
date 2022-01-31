package com.taskify.authorization.service.impl;

import com.taskify.authorization.data.request.LoginRequest;
import com.taskify.authorization.data.request.UserData;
import com.taskify.authorization.data.request.UserRequest;
import com.taskify.authorization.data.response.LoginResponse;
import com.taskify.authorization.entity.User;
import com.taskify.authorization.exception.AlreadyExistsException;
import com.taskify.authorization.exception.BadRequestException;
import com.taskify.authorization.repository.UserRepository;
import com.taskify.authorization.security.jwt.JwtUtils;
import com.taskify.authorization.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private PasswordEncoder passwordEncoder;
    private final static String DEFAULT_PASSWORD = "Abc123456";

    @Override
    public void create(@NonNull UserRequest userRequest) throws BadRequestException, AlreadyExistsException {
        checkEmail(userRequest.getEmail());

        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setOrganizationId(userRequest.getOrganizationId());

        userRepository.save(user);
    }

    @Override
    public User getByEmail(@NonNull String email) {
        return userRepository.findByEmailContainingIgnoreCase(email).orElse(null);
    }

    @Override
    public Boolean verifyOrganizationUsers(@NonNull UserData userData) {
        User creator = userRepository.findById(userData.getCreatorId()).orElse(null);
        if (creator == null){
            return false;
        }
        for (UUID userId: userData.getAssignees()){
            User assignee = userRepository.findById(userId).orElse(null);
            if (assignee == null || !assignee.getOrganizationId().equals(creator.getOrganizationId())){
                return false;
            }
        }

        return true;
    }

    @Override
    public LoginResponse login(@NonNull LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        UserDetails userDetails= loadUserByUsername(loginRequest.getEmail());
        String accessToken = jwtUtils.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        return loginResponse;
    }

    private void checkEmail(@NonNull String email) throws BadRequestException, AlreadyExistsException {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new BadRequestException("Invalid email pattern");
        }

        Boolean isEmailOccupied = userRepository.existsByEmailContainingIgnoreCase(email);
        if (isEmailOccupied){
            throw new AlreadyExistsException(String.format("Admin with email: %s already exists", email));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException(String.format("User with email: %s not found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
