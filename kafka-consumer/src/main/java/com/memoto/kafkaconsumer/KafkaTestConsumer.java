package com.memoto.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaTestConsumer
{
    public static void main( String[] args )
    {
        SpringApplication.run(KafkaTestConsumer.class, args);
    }
}
