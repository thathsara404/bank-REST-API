package com.bank.service.service.message.specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represent the specification which includes transaction-message-queue related business logic operations.
 * */
public interface ITransactionMessageService {

    /**
     * Build transaction failure message to push to the queue
     * @param transactionId String
     * @param transactionDateTime LocalDateTime
     * */
    public abstract void buildAndPushTransactionFailureMessage(String transactionId,
                                                               LocalDateTime transactionDateTime
    );

    /**
     * Build transaction placement message to push to the queue
     * @param transactionId String
     * @param transactionDateTime LocalDateTime
     * */
    public abstract void buildAndPushTransactionPlacementMessage(String transactionId,
                                                               LocalDateTime transactionDateTime
    );

}
