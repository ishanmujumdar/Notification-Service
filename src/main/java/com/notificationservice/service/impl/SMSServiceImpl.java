package com.notificationservice.service.impl;

import com.notificationservice.entity.SMS;
import com.notificationservice.repository.sql.SMSRepository;
import com.notificationservice.service.SMSService;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    private SMSRepository smsRepository;

    @Override
    public SMS addSMS(SMS sms) {
        return smsRepository.save(sms);
    }

    @Override
    public SMS createSMS(String phone_number, String message) {
        SMS newSMS = new SMS();
        newSMS.setPhoneNumber(phone_number);
        newSMS.setMessage(message);
        newSMS.setStatus("Pending");
        newSMS.setCreatedAt(LocalDateTime.now());
        newSMS.setUpdatedAt(LocalDateTime.now());
        return newSMS;
    }

    @Override
    public SMS getSMS(Long id) {
        Optional<SMS> optionalSMS = smsRepository.findById(id);
        SMS sms = optionalSMS.orElse(null);
        return sms;
    }

    @Override
    public JSONObject getRequestBody3rdPartyAPI(SMS sms) {

        String correlationId = "some-correlation-id";

        JSONObject objJson = new JSONObject();
        JSONObject channelsJson = new JSONObject();
        JSONArray destinationJsonArr = new JSONArray();
        JSONObject smsJson = new JSONObject();
        JSONArray msisdn = new JSONArray();
        JSONObject destinationJSONobj = new JSONObject();

        smsJson.put("text", sms.getMessage());

        // channels
        channelsJson.put("sms", smsJson);


        // destination
        msisdn.put(sms.getPhoneNumber());
        destinationJSONobj.put("msisdn", msisdn);
        destinationJSONobj.put("correlationId" , correlationId);
        destinationJsonArr.put(destinationJSONobj);

        // objJson
        objJson.put("deliverychannel", "sms");
        objJson.put("channels", channelsJson);
        objJson.put("destination", destinationJsonArr);

        return objJson;
    }

}
