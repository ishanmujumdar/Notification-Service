package com.notificationservice.controller;

import com.notificationservice.handler.SMSRequestHandler;
import com.notificationservice.entity.ResponseEntities.GenericResponse;
import com.notificationservice.entity.SMS;
import com.notificationservice.entity.RequestEntities.SMSRequestEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("v1/")
public class SMSController {

    @Autowired
    private SMSRequestHandler smsRequestHandler;

    @PostMapping("sms/send")
    public GenericResponse<SMS> SMSRequest(@RequestBody SMSRequestEntity smsRequestEntity) {
        try {
            SMS sms = smsRequestHandler.smsRequestHandle(smsRequestEntity);
            return GenericResponse.<SMS>builder().success(true).data(sms).build();
        } catch (Exception e) {
            return GenericResponse.<SMS>builder().success(false).error("Provide a valid phone number").build();
        }
    }

    @GetMapping("sms/{request_id}")
    @ResponseBody
    public GenericResponse<SMS> getDetailsFromDB(@PathVariable("request_id") Long id) {
        try {
            SMS sms = smsRequestHandler.getDetailsFromDBHandle(id);
            return GenericResponse.<SMS>builder().success(true).data(sms).build();
        } catch (Exception e) {
            return GenericResponse.<SMS>builder().success(false).error(e.getMessage()).build();
        }
    }
}
