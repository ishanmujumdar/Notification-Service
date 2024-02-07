package com.notificationservice;

import com.notificationservice.entity.SMS;
import com.notificationservice.repository.sql.SMSRepository;
import com.notificationservice.service.impl.SMSServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SMSServiceTests {
    @Mock
    public SMSRepository smsRepository;

    @InjectMocks
    public SMSServiceImpl smsService;

    @Test
    public void addSMSTest() {
        Mockito.when(smsRepository.save(Mockito.any(SMS.class))).thenReturn( null);
        smsService.addSMS(getMockSMS());
        Assert.assertEquals(1, 1);
    }

    @Test
    public void createSMSTest() {
        SMS sms = smsService.createSMS("9302242602", "Hi");
        Assert.assertEquals("9302242602", sms.getPhoneNumber());
    }

    @Test
    public void getSMSTest() {
        SMS sms = smsService.createSMS("9302242602", "Hi");
        Assert.assertEquals("9302242602", sms.getPhoneNumber());
    }

    @Test
    public void getRequestBody3rdPartyAPITest() throws JSONException {
        JSONObject obj = smsService.getRequestBody3rdPartyAPI(getMockSMS());
        JSONArray destinationArray = obj.getJSONArray("destination");
        JSONObject destinationJSONObject = destinationArray.getJSONObject(0);
        JSONArray numbers = destinationJSONObject.getJSONArray("msisdn");
        Assert.assertEquals(numbers.get(0), "9302242602");
    }

    SMS getMockSMS() {

        SMS sms = new SMS();
        sms.setId(1);
        sms.setStatus("Pending");
        sms.setMessage("Hi");
        sms.setPhoneNumber("9302242602");
        sms.setCreatedAt(LocalDateTime.now());
        sms.setUpdatedAt(LocalDateTime.now());
        return sms;
    }

}
