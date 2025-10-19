package com.example.memoto.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = "memo-topic", groupId = "memo-created")
    public void listen() {
        System.out.println("Received message in the KafkaListener memo-created topic");
    }

    @KafkaListener(topics = "memo-topic", groupId = "memo-deleted")
    public void listenTest() {
        System.out.println("Received message in the test topic");
    }

}

