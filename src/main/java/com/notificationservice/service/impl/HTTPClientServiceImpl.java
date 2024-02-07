package com.notificationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notificationservice.entity.SMS;
import com.notificationservice.service.HTTPClientService;
import com.notificationservice.service.SMSService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.UnsupportedEncodingException;
import java.util.Collections;


@Service
public class HTTPClientServiceImpl implements HTTPClientService {

    @Autowired
    private SMSService smsService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendMessage(SMS sms) {
        String url = "https://api.imiconnect.in/resources/v1/messaging";
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        headers.add("Key", "c0c49ebf-ca44-11e9-9e4e-025282c394f2");

        // request body
        JSONObject requestBody = smsService.getRequestBody3rdPartyAPI(sms);

        // Build the reqeust
        HttpEntity<String> request =
                new HttpEntity<String>(requestBody.toString(), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

