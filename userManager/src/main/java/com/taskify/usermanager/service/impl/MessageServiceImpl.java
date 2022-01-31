package com.taskify.usermanager.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskify.usermanager.data.request.UserRequest;
import com.taskify.usermanager.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
    private ObjectMapper objectMapper;
    private Queue queue;

    @Override
    public UUID getOrganizationIdByToken(String token) {
        Message message = MessageBuilder.withBody(token.getBytes()).build();
        Object response  = rabbitTemplate.convertSendAndReceive(directExchange.getName(), "adminkey", message);
        if (response == null) return null;
        return UUID.nameUUIDFromBytes((byte[]) response);
    }

    @Override
    public void sendUser(UserRequest userRequest) {
        try {
            String userString = objectMapper.writeValueAsString(userRequest);
            Message message = MessageBuilder
                    .withBody(userString.getBytes())
                    .setContentType("application/json")
                    .build();
            rabbitTemplate.convertAndSend(queue.getName(),message);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
