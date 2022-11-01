package com.bank.service.exception;

/**
 * Exception for unexpected data.
 * */
public class UnExpectedResultFoundException extends RuntimeException{

    public UnExpectedResultFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
