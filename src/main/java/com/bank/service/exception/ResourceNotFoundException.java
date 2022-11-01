package com.bank.service.exception;

/**
 * Exception for account not found
 * */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
