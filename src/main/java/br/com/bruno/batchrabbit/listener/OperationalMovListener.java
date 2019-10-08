package br.com.bruno.batchrabbit.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author Bruno Andrade
 * Dia: 05/10/2019
 **/
@Component
public class OperationalMovListener {

    private static final Logger log = LoggerFactory.getLogger(OperationalMovListener.class);

    @RabbitListener(queues = "spring-batch-operational-mov-queue")
    public void consumeDefaultMessage(final Message message) {
        log.info("Recceived message, OM is {}", message.toString());
    }
}
