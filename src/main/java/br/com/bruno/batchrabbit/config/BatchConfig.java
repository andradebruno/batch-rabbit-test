package br.com.bruno.batchrabbit.config;

import br.com.bruno.batchrabbit.domain.OperationalMovimentation;
import br.com.bruno.batchrabbit.mapper.OperationalMovRowMapper;
import br.com.bruno.batchrabbit.processor.OperationalMovProcessor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.amqp.AmqpItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;
import java.util.Date;

/**
 * @author Bruno Andrade
 * Dia: 07/10/2019
 **/
@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobs;

    @Autowired
    public StepBuilderFactory steps;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public AmqpTemplate amqpTemplate;

    @Autowired
    public JobLauncher jobLauncher;

    @Scheduled(fixedDelay = 3000L)
    public void sendSmsForExpiringBookmark() throws Exception
    {
        System.out.println(" Job Started at :"+ new Date());
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(importUserJob(), param);
        System.out.println("Job finished with status :" + execution.getStatus());
    }


    @Bean
    public Job importUserJob() {
        return jobs.get("databaseToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1(amqpTemplate)).end()
                .build();
    }

    @Bean
    public Step step1(final AmqpTemplate amqpTemplate) {
         return steps.get("databaseToDatabaseStep")
                .<OperationalMovimentation, OperationalMovimentation> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer(amqpTemplate))
                .build();

    }


    @Bean
    public ItemStreamReader<OperationalMovimentation> reader() {
        JdbcCursorItemReader<OperationalMovimentation> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT cod_transaction, cod_costumer, account_to, account_from, value FROM OPERATIONAL_MOVIMENTATION");
        reader.setRowMapper(new OperationalMovRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<OperationalMovimentation, OperationalMovimentation> processor() {
        return new OperationalMovProcessor();
    }

    @Bean
    public AmqpItemWriter writer(AmqpTemplate amqpTemplate) {
        AmqpItemWriter<OperationalMovimentation> itemWriter = new AmqpItemWriter(amqpTemplate);
        return itemWriter;
    }

}
