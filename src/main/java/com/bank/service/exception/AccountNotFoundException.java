package com.bank.service.exception;

/**
 * Exception for account not found
 * */
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException (String message, Throwable cause) {
        super(message, cause);
    }

}
