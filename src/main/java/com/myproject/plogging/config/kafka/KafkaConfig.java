package com.myproject.plogging.config.kafka;

import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfig {
    @Bean
    public ProducerFactory<String, Long> producerFactory(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, NumberSerializers.LongSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);

    }

    @Bean
    public KafkaTemplate<String, Long> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
