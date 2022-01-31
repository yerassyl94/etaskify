package com.taskify.authorization.service.impl;

import com.taskify.authorization.data.request.UserData;
import com.taskify.authorization.data.request.UserRequest;
import com.taskify.authorization.entity.User;
import com.taskify.authorization.security.jwt.JwtUtils;
import com.taskify.authorization.service.MessageService;
import com.taskify.authorization.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class MessageServiceImpl implements MessageService {
    private JwtUtils jwtUtils;
    private UserService userService;

    @Override
    @RabbitListener(queues = "${rabbitmq.auth.queue.name}")
    public UUID getUserIdFromReceivedToken(Map<String, String> tokenMap) {
        log.info("Received user token");
        if (!tokenMap.containsKey("token") || tokenMap.get("token").isEmpty()) {
            return null;
        }

        if (!jwtUtils.validateToken(tokenMap.get("token"))) {
            return null;
        }

        String email = jwtUtils.extractUsername(tokenMap.get("token"));
        User user = userService.getByEmail(email);
        log.info("Returning user id");
        return user.getId();
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.user.queue.name}")
    public Boolean verifyOrganizationUserList(UserData userData) {
        return userService.verifyOrganizationUsers(userData);
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveNewUser(UserRequest userRequest){
        userService.create(userRequest);
    }
}
