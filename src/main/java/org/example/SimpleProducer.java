package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleProducer {

    private final static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);
    private final static String TOPIC_NAME = "test";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        var configs = new Properties();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (var producer = new KafkaProducer<String, String>(configs)) {

            String messageValue = "testMessage";
            var nonKeyRecord = new ProducerRecord<String, String>(TOPIC_NAME, messageValue);
            var keyRecord = new ProducerRecord<>(TOPIC_NAME, "pangyo", "this key value");
            var recordKeyAndPartition = new ProducerRecord<>(TOPIC_NAME, 0, "key1", "this value");

            producer.send(nonKeyRecord);
            producer.send(keyRecord);
            producer.send(recordKeyAndPartition);

            logger.info("{}", nonKeyRecord);

            producer.flush();
        }
    }
}
