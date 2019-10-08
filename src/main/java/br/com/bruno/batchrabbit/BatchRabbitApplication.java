package br.com.bruno.batchrabbit;

import br.com.bruno.batchrabbit.domain.OperationalMovimentation;
import br.com.bruno.batchrabbit.repository.OperationalMovRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
@EnableJpaRepositories(basePackages = "br.com.bruno.batchrabbit.repository")
public class BatchRabbitApplication {

   /* public static final String EXCHANGE_NAME = "tips_tx";
    public static final String DEFAULT_PARSING_QUEUE = "default_parser_q";
    public static final String ROUTING_KEY = "tips";*/

	public static void main(String[] args) {
		SpringApplication.run(BatchRabbitApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(OperationalMovRepository operationalMovRepository) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<OperationalMovimentation>> typeReference = new TypeReference<List<OperationalMovimentation>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/oper-mov.json");
            try {
                List<OperationalMovimentation> operationalMovimentationList = mapper.readValue(inputStream, typeReference);
                operationalMovRepository.saveAll(operationalMovimentationList);
                System.out.println("Operational Movimentation Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save operational movimentations: " + e.getMessage());
            }
        };
    }

   /* @Bean
    public TopicExchange tipsExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue defaultParsingQueue() {
        return new Queue(DEFAULT_PARSING_QUEUE);
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }*/

}
