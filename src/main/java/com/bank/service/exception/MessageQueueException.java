package com.bank.service.exception;

/**
 * Exception for unexpected RabbitMQ operation failure
 * */
public class MessageQueueException extends RuntimeException{

    public MessageQueueException (String message, Throwable cause) {
        super(message, cause);
    }

}
