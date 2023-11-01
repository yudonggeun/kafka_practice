package org.example.spring.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

@SpringBootApplication
public class SpringBatchConsumerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringConsumerApplication.class);
        application.run(args);
    }

    @KafkaListener(topics = "test",
            groupId = "test-group-01")
    public void batchListener(ConsumerRecords<String, String> records) {
        records.forEach(System.out::println);
    }

    @KafkaListener(topics = "test",
            groupId = "test-group-02")
    public void batchListener(List<String> list) {
        list.forEach(System.out::println);
    }

    @KafkaListener(topics = "test",
            groupId = "test-group-03",
            concurrency = "3")
    public void concurrentBatchListener(ConsumerRecords<String, String> records) {
        records.forEach(System.out::println);
    }

}