package sap.commerce.org.epcservice.rabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static final String LISTENER_METHOD = "receiveMessage";

    @Value("${epcservice.rabbitmq.receive-queue}")
    private String receiveQueue;

    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory,
                                             final MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(receiveQueue);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final KymaConsumer consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }
}
