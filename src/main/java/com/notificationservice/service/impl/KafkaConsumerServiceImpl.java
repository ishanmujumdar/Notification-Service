package com.notificationservice.service.impl;

import com.notificationservice.entity.SMS;
import com.notificationservice.entity.SMSElastic;
//import com.notificationservice.repository.elastic.SMSElasticRepository;
//import com.notificationservice.service.ElasticService;
import com.notificationservice.repository.elastic.SMSElasticRepository;
import com.notificationservice.service.HTTPClientService;
import com.notificationservice.service.KafkaConsumerService;
import com.notificationservice.service.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Component
public final class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HTTPClientService httpClientService;

//    @Autowired
//    private ElasticService elasticService;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private SMSElasticRepository smsElasticRepository;

    @Autowired
    private SMSService smsService;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    @KafkaListener(topics = "messageIdTopic", groupId = "group_id")
    public void consume(String id) throws IOException {
        logger.info(String.format("$$$$ => Consumed id: %s", id));

        // Get sms
        SMS sms = smsService.getSMS(Long.valueOf(id));

        // Check if number is blacklisted
        Boolean isBlacklisted = redisTemplate.opsForSet().isMember("blacklisted", sms.getPhoneNumber());

        // Set status

        String status;
        if(isBlacklisted == false) {
            status = "Verified";
            // send message through 3rd party api
        } else {
            status = "Blacklisted";
        }

        // Update status
        sms.setStatus(status);

        // Index in elastic search
        SMSElastic smsElastic = new SMSElastic(sms);
        try {
            smsElasticRepository.save(smsElastic);
        } catch (Exception e) {}

        // Send message
        if(!status.equals("Blacklisted"))
            httpClientService.sendMessage(sms);

        // Update in db
        smsService.addSMS(sms);
    }
}
