package com.taskify.taskmanager.config;

import com.taskify.taskmanager.exception.AccessDeniedException;
import com.taskify.taskmanager.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private MessageService messageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headerString = request.getHeader("Authorization");
        if (headerString == null || !headerString.contains("Bearer")) {
            throw new AccessDeniedException();
        }
        String token = request.getHeader("Authorization").substring(7);
        UUID userId = messageService.getUserIdByToken(token);
        if (userId == null) {
            throw new AccessDeniedException();
        }
        request.setAttribute("userId",userId);
        return true;
    }

}
