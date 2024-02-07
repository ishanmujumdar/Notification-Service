package com.notificationservice.handler;

import com.notificationservice.entity.RequestEntities.SMSRequestEntity;
import com.notificationservice.entity.SMS;
import com.notificationservice.service.KafkaProducerService;
import com.notificationservice.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class SMSRequestHandler {
    @Autowired
    private SMSService smsService;

    @Autowired
    private KafkaProducerService producerService;

    public SMS smsRequestHandle (SMSRequestEntity smsRequestEntity) {
        String phoneNumber = smsRequestEntity.getPhone_number();
        String message = smsRequestEntity.getMessage();

        if(phoneNumber == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Enter a valid phone number"
            );
        }


        SMS newSMS = smsService.createSMS(phoneNumber, message);
        smsService.addSMS(newSMS);
        producerService.sendMessageKafka("messageIdTopic", String.valueOf(newSMS.getId()));
        SMS sms = smsService.getSMS(newSMS.getId());
        return sms;
    }

    public SMS getDetailsFromDBHandle(Long id) {
        SMS sms = smsService.getSMS(id);

        if(sms == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "request_id not found"
            );
        }

        return sms;
    }


}
