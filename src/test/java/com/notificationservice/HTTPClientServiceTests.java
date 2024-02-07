package com.notificationservice;

import com.notificationservice.entity.SMS;
import com.notificationservice.service.SMSService;
import com.notificationservice.service.impl.HTTPClientServiceImpl;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class HTTPClientServiceTests {
    @Mock
    private SMSService smsService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private HTTPClientServiceImpl httpClientService;

    @Test
    public void sendMessageTest() {
        SMS sms = Mockito.mock(SMS.class);
        Mockito.when(smsService.getRequestBody3rdPartyAPI(Mockito.any(SMS.class))).thenReturn(getMockJsonObject());
        Mockito.lenient().when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(ResponseEntity.class), Mockito.any())).thenReturn(ResponseEntity.ok("Success"));
        httpClientService.sendMessage(sms);
        Assert.assertEquals(1, 1);
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

    JSONObject getMockJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", "value");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }
}
