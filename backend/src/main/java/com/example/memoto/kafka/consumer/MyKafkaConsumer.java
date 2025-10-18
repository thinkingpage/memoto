package com.example.memoto.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "memoto-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
