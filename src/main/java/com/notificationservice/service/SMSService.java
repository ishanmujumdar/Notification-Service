package com.notificationservice.service;

import com.notificationservice.entity.SMS;
import org.json.JSONObject;

public interface SMSService {
    SMS addSMS (SMS sms);
    SMS createSMS(String phone_number, String message);
    SMS getSMS(Long id);
    JSONObject getRequestBody3rdPartyAPI(SMS sms);
}
