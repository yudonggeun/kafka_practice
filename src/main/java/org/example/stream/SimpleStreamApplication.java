package org.example.stream;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.example.producer.SimpleProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleStreamApplication {
    private final static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String APPLICATION_NAME = "streams-application";
    private final static String STREAM_LOG = "test";
    private final static String STREAM_LOG_COPY = "stream_log_copy";

    public static void main(String[] args) {
        var props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.String().getClass());

        var builder = new StreamsBuilder();
        KStream<String, String> streamLog = builder.stream(STREAM_LOG);
        streamLog.to(STREAM_LOG_COPY);

        var streams = new KafkaStreams(builder.build(), props);
        streams.start();

    }
}
