package com.fastcampus.kafkahandson.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class MySecondConsumer {
    @KafkaListener(topics = "my-second-topic",
            groupId = "my-consumer-group",
            containerFactory = "secondKafkaListenerContainerFactory")
    public void accept(ConsumerRecord<String, String> message) {
        System.out.println("Second consumer Message arrived! - " + message.value());
    }
}
