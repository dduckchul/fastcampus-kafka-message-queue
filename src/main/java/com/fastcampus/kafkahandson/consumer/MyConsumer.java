package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer {
    @KafkaListener(topics = "my-json-topic", groupId = "my-consumer-group")
    public void accept(ConsumerRecord<String, MyMessage> message) {
        System.out.println("Main consumer Message arrived! - " + message.value());
    }
}
