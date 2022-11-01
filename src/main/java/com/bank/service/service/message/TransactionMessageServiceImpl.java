package com.bank.service.service.message;

import com.bank.service.exception.MessageQueueException;
import com.bank.service.repository.message.TransactionRepositoryImpl;
import com.bank.service.service.message.specification.ITransactionMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementation of TransactionServiceImpl
 * */
@Service
@AllArgsConstructor
public class TransactionMessageServiceImpl implements ITransactionMessageService {

    private TransactionRepositoryImpl transactionRepository;

    /**
     * Build transaction failure message to push to the queue
     * @param transactionId String
     * @param transactionDateTime LocalDateTime
     * */
    @Override
    public void buildAndPushTransactionFailureMessage(String transactionId,
                                                      LocalDateTime transactionDateTime) {
        try {
            StringBuffer message = new StringBuffer("{tid: ");
            message.append(transactionId).append(", ")
                    .append("tdatetime: ").append(transactionDateTime)
                    .append("}");
            byte[] messageBodyBytes = message.toString().getBytes();
            transactionRepository.pushFailureMessage(messageBodyBytes);
        } catch (MessageQueueException messageQueueException) {
            throw messageQueueException;
        } catch (Exception exception) {
            throw new MessageQueueException(exception.getMessage(), exception.getCause());
        }
    }

    /**
     * Build transaction placement message to push to the queue
     * @param transactionId String
     * @param transactionDateTime LocalDateTime
     * */
    @Override
    public void buildAndPushTransactionPlacementMessage(String transactionId, LocalDateTime transactionDateTime) {
        try {
            byte[] messageBodyBytes = transactionId.getBytes();
            transactionRepository.pushProcessMessage(messageBodyBytes);
        } catch (MessageQueueException messageQueueException) {
            throw messageQueueException;
        } catch (Exception exception) {
            throw new MessageQueueException(exception.getMessage(), exception.getCause());
        }

    }
}
