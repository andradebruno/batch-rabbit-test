package br.com.bruno.batchrabbit.service;

import br.com.bruno.batchrabbit.domain.OperationalMovimentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Bruno Andrade
 * Dia: 05/10/2019
 **/
@Service
public class ProducerService {

    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);

    @Value("${app.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@Scheduled(fixedDelay = 3000L)
    public void publishMessage(OperationalMovimentation operationalMovimentation) {
        //OperationalMovimentation op = new OperationalMovimentation("UUID", "COSTUMER:0001", 1,2, new BigDecimal(20));
        rabbitTemplate.convertAndSend(exchange, routingKey, operationalMovimentation);
        log.info("Mensagem enviada!");
    }
}
