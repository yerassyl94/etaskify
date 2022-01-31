package com.taskify.usermanager.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Access denied");
    }
}
