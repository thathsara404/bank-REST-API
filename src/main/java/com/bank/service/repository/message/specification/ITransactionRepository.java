package com.bank.service.repository.message.specification;

/**
 * Represent the specification which includes transaction message related operations.
 * */
public interface ITransactionRepository {

    /**
     * Push transaction failure messages to queues
     * @param messageBytes byte[]
     * */
    public abstract void pushFailureMessage(byte[] messageBytes);

    /**
     * Push process messages to queues
     * @param messageBytes byte[]
     * */
    public abstract void pushProcessMessage(byte[] messageBytes);

}
