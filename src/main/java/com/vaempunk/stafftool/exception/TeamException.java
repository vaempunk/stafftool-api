package com.vaempunk.stafftool.exception;

public class TeamException extends Exception {

    private EntityExceptionType type;

    public TeamException(EntityExceptionType type) {
        super();
        this.type = type;
    }

    public EntityExceptionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + " | ExceptionType: " + type;
    }

}
