package com.bank.service.service.schema.specification;

import com.bank.service.dto.TransactionDTO;

import java.util.UUID;

/**
 * Represent the specification which includes transaction related business logic operations.
 * */
public interface ITransactionService {

    /**
     * Save new transaction
     * @param transaction TransactionDTO
     * @param accountNumber UUID
     * @return TransactionDTO
     * */
    public abstract TransactionDTO addNewTransaction(TransactionDTO transaction, UUID accountNumber);

}
