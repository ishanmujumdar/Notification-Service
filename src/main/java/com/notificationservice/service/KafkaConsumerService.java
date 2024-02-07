package com.notificationservice.service;

import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

public interface KafkaConsumerService {
    public void consume(String message) throws IOException;
}
