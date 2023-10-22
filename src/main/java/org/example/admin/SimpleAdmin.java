package org.example.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.config.ConfigResource;
import org.example.producer.SimpleProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class SimpleAdmin {
    private final static Logger logger = LoggerFactory.getLogger(SimpleProducer.class);
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var configs = new Properties();

        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        var admin = AdminClient.create(configs);

        logger.info("== get borker information");

        for (Node node : admin.describeCluster().nodes().get()) {
            logger.info("node : {}", node);
            var cr = new ConfigResource(ConfigResource.Type.BROKER, node.idString());

            var describeConfigs = admin.describeConfigs(Collections.singleton(cr));
            describeConfigs.all().get().forEach((broker, config) -> {
                config.entries().forEach(configEntry -> {
                    logger.info((configEntry.name() + "= " + configEntry.value()));
                });
            });
        }
    }
}
