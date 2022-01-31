package com.taskify.usermanager.config;

import com.taskify.usermanager.exception.AccessDeniedException;
import com.taskify.usermanager.service.MessageService;
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
        UUID organizationId = messageService.getOrganizationIdByToken(token);
        if (organizationId == null) {
            throw new AccessDeniedException();
        }
        request.setAttribute("organizationId",organizationId);
        return true;
    }

}
