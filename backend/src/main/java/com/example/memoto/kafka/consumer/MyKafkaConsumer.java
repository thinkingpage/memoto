package com.example.memoto.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = "memo-topic", groupId = "memo-group")
    public void memoConsumer(String info) {
        switch(info) {
            case "memo-created":
                System.out.println("CREATE RECEIVED");
                break;
            case "memo-deleted":
                System.out.println("DELETE RECEIVED");
                break;
            default:
                System.out.println("UNKNOWN EVENT");
        }
    }
}

