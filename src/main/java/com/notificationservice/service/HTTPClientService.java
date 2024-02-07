package com.notificationservice.service;

import com.notificationservice.entity.SMS;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface HTTPClientService {

    void sendMessage(SMS sms);
}
