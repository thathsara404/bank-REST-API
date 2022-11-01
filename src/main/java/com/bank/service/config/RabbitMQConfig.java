package com.bank.service.config;

import com.rabbitmq.client.*;
import com.bank.service.consts.MessageRoutingConst;
import com.bank.service.function.PentaArgsFunction;
import com.bank.service.function.TriArgsFunction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;

/**
 * RabbitMQ connection bean configurations.
 * */
@Configuration
public class RabbitMQConfig {

    /**
     * Connection Bean Definition.
     * @param username
     * @param password
     * @param virtualHost
     * @param port
     * @param host
     * @return ConnectionFactory
     * */
    @Qualifier("connection")
    @Bean
    @Scope("singleton")
    public Connection getConnection(
            @Value("${rabbitMQ.username}")final String username,
            @Value("${rabbitMQ.password}")final String password,
            @Value("${rabbitMQ.virtualHost}")final String virtualHost,
            @Value("${rabbitMQ.port}")final Integer port,
            @Value("${rabbitMQ.host}")final String host
    ) throws IOException, TimeoutException {
        Connection connection = null;
            connection = getConnection.apply(host, port, username, password, virtualHost);
            return connection;
    }

    /**
     * Channel Bean Definition
     * @param connection Connection
     * @param msgQueueProp Map<String, String>
     * @return Channel
     * */
    @Qualifier("channel")
    @Bean
    @Scope("singleton")
    public Channel getChannel(@Qualifier("connection") final Connection connection,
                              @Qualifier("messageQueueProp") final Map<String, String> msgQueueProp
                              ) {
        return getChannel.apply(connection,
                msgQueueProp.get("exchangeName"), MessageRoutingConst.SUCCESS_ROUTING_MESSAGE);
    }

    /**
     * Message Queue Prop Bean Definition
     * @param exchangeName String
     * */
    @Qualifier("messageQueueProp")
    @Bean
    @Scope("singleton")
    public Map<String, String> getMessageQueueProps (
            @Value("${rabbitMQ.webApp.exchangeName}") final String exchangeName,
            @Value("${rabbitMQ.username}") final String userId
    ) {
        return getMessageQueueProps.apply(exchangeName, userId);
    }

    private PentaArgsFunction<String, Integer, String, String, String, Connection> getConnection = (
            String host,
            Integer port,
            String username,
            String password,
            String virtualHost
    ) -> {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setHost(host);
        connectionFactory.setPort(5672);
        return connectionFactory.newConnection();
    };

    private TriArgsFunction<Connection, String, String, Channel> getChannel = (
            Connection connection,
            String exchangeName,
            String routingKey
            ) -> {
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true);
            // I need this publisher to publish messages for transaction.success and transaction.* routing keys
            // So U do not bind this to specific queue.
            // String queueName = channel.queueDeclare().getQueue();
            // channel.queueBind(queueName, exchangeName, routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    };

    private BiFunction<String, String, Map<String, String>> getMessageQueueProps= (
            String exchangeName,
            String userId
            ) -> {
        Map<String, String> messageQueueProps = new HashMap<>();
        messageQueueProps.put("exchangeName", exchangeName);
        messageQueueProps.put("userId", userId);
        return messageQueueProps;
    };

}
