package com.taskify.taskmanager.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Access denied");
    }
}
