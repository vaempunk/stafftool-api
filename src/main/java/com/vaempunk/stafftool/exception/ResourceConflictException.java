package com.vaempunk.stafftool.exception;

public class ResourceConflictException extends RuntimeException {
    public ResourceConflictException() {
        super("Resource already exists");
    }
}
