package com.notificationservice.service;

public interface KafkaProducerService {
    public void sendMessageKafka(String topicName, String id);
}
