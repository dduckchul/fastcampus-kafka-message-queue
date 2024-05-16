package com.fastcampus.kafkahandson.producer;

import com.fastcampus.kafkahandson.model.MyMessage;
import com.fastcampus.kafkahandson.model.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MySecondProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageWithKey(String key, String message) {
        kafkaTemplate.send(Topic.MY_SECOND_TOPIC, key, message);
    }
}
