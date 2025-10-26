package com.memoto.kafkaconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "memo-topic", groupId = "kafka-test-consumer")
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

