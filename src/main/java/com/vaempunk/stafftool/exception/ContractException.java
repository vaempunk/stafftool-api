package com.vaempunk.stafftool.exception;

public class ContractException extends Exception {

    private EntityExceptionType type;

    public ContractException(EntityExceptionType type) {
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
