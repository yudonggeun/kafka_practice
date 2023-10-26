package org.example.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import java.util.Properties;

public class SimpleKafkaProcessor {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String APPLICATION_NAME = "processor-application";
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

        var topology = new Topology();

        topology.addSource("Source", STREAM_LOG)
                .addProcessor("Process",
                        () -> new FilterProcessor(),
                        "Source")
                .addSink("Sink",
                        STREAM_LOG_COPY,
                        "Process");

        var streaming = new KafkaStreams(topology, props);
        streaming.start();
    }
}
