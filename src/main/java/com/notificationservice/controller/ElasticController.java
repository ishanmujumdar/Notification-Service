package com.notificationservice.controller;

import com.notificationservice.handler.ElasticControllerHandler;
import com.notificationservice.entity.RequestEntities.ElasticTimeSearchRequestEntity;
import com.notificationservice.entity.ResponseEntities.GenericResponse;
import com.notificationservice.entity.RequestEntities.ElasticTextSearchRequestEntity;
import com.notificationservice.entity.SMSElastic;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class ElasticController {

    @Autowired
    private ElasticControllerHandler elasticControllerHandler;

    @PostMapping("/sms/search")
    GenericResponse<List<SMSElastic>> searchTextElastic(@RequestBody ElasticTextSearchRequestEntity elasticTextSearchRequestEntity) {
        try {
            List<SMSElastic> results = elasticControllerHandler.searchTextElasticHandle(elasticTextSearchRequestEntity);
            return GenericResponse.<List<SMSElastic>>builder().success(true).data(results).build();
        } catch (Exception e) {
            return GenericResponse.<List<SMSElastic>>builder().success(false).error("Unable to process the request").build();
        }
    }

    @PostMapping("/sms/time")
    GenericResponse<List<SMSElastic>> getMessagesBetweenAtime(@RequestBody ElasticTimeSearchRequestEntity elasticTimeSearchRequestEntity) {
        try {
            List<SMSElastic> results = elasticControllerHandler.getMessagesBetweenAtimeHandle(elasticTimeSearchRequestEntity);
            return GenericResponse.<List<SMSElastic>>builder().success(true).data(results).build();
        } catch (Exception e) {
            return GenericResponse.<List<SMSElastic>>builder().success(false).error("Unable to process the request").build();
        }
    }
}
