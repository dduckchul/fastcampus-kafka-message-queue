package com.fastcampus.kafkahandson.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecondKafkaConfig {
    @Bean
    @Qualifier("secondKafkaProperties")
    @ConfigurationProperties("spring.kafka.string")
    public KafkaProperties secondKafkaProperties() {
        return new KafkaProperties();
    }
    @Bean
    @Qualifier("secondConsumerFactory")
    public ConsumerFactory<String, Object> secondConsumerFactory(KafkaProperties secondKafkaProperties){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, secondKafkaProperties.getBootstrapServers());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, secondKafkaProperties.getConsumer().getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, secondKafkaProperties.getConsumer().getValueDeserializer());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    @Qualifier("secondKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Object> secondKafkaListenerContainerFactory(ConsumerFactory<String,Object> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        return factory;
    }

    @Bean
    @Qualifier("secondProducerFactory")
    public ProducerFactory<String, Object> secondProducerFactory(KafkaProperties secondKafkaProperties){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, secondKafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, secondKafkaProperties.getProducer().getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, secondKafkaProperties.getProducer().getValueSerializer());
        props.put(ProducerConfig.ACKS_CONFIG, secondKafkaProperties.getProducer().getAcks());
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    @Qualifier("secondKafkaTemplate")
    public KafkaTemplate<String, ?> secondKafkaTemplate(KafkaProperties kafkaProperties){
        return new KafkaTemplate<>(secondProducerFactory(kafkaProperties));
    }
}
