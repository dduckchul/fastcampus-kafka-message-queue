package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyConsumer implements Consumer<Message<MyMessage>> {

    MyConsumer() {
        System.out.print("MyConsumer is created");
    }
    @Override
    public void accept(Message<MyMessage> myMessageMessage) {
        System.out.print("Message Arrived = " + myMessageMessage.getPayload());
    }
}
