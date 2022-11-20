package com.bank.service.repository.message;

import com.bank.service.repository.message.specification.ITransactionRepository;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.bank.service.consts.ErrorConst;
import com.bank.service.exception.MessageQueueException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.function.Function;

import static com.bank.service.consts.MessageRoutingConst.*;

/**
 * Implementation of ITransactionRepository
 * */
@Repository
@AllArgsConstructor
public class TransactionRepositoryImpl implements ITransactionRepository {

    private Channel channel;

    @Qualifier("messageQueueProp")
    private Map<String, String> messageQueueProps;

    /**
     * Push transaction failure message to queue
     * @param messageBytes byte[]
     * */
    @Override
    public void pushFailureMessage(byte[] messageBytes) {
        try {
            channel.basicPublish(messageQueueProps.get("exchangeName"),
                    FAILURE_ROUTING_MESSAGE, // Routing Key
                    basicPropertiesAMPQ.apply(messageQueueProps.get("userId")), messageBytes);
        } catch (Exception exception) {
            throw new MessageQueueException(ErrorConst.MESSAGE_QUE_OPERATION_FAILURE_MESSAGE, null);
        }
    }

    /**
     * Push process messages to queues
     * @param messageBytes byte[]
     * */
    @Override
    public void pushProcessMessage(byte[] messageBytes) {
        try {
            channel.basicPublish(messageQueueProps.get("exchangeName"),
                    PROCESS_ROUTING_MESSAGE, // Routing Key
                    basicPropertiesAMPQ.apply(messageQueueProps.get("userId")), messageBytes);
        } catch (Exception exception) {
            throw new MessageQueueException(ErrorConst.MESSAGE_QUE_OPERATION_FAILURE_MESSAGE, null);
        }
    }

    private final Function<String, AMQP.BasicProperties> basicPropertiesAMPQ = (
            String username
    ) -> new AMQP.BasicProperties.Builder().userId(username).build();

}
