package com.notificationservice.handler;

import com.notificationservice.entity.RequestEntities.ElasticTextSearchRequestEntity;
import com.notificationservice.entity.RequestEntities.ElasticTimeSearchRequestEntity;
import com.notificationservice.entity.SMSElastic;
import com.notificationservice.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ElasticControllerHandler {
    @Autowired
    private ElasticService elasticService;

    public List<SMSElastic> searchTextElasticHandle(ElasticTextSearchRequestEntity elasticTextSearchRequestEntity) {
        String text = elasticTextSearchRequestEntity.getText();
        Integer page =  elasticTextSearchRequestEntity.getPage();
        Integer size = elasticTextSearchRequestEntity.getSize();

        return elasticService.searchTextElastic(text, page, size);
    }

    public List<SMSElastic> getMessagesBetweenAtimeHandle(ElasticTimeSearchRequestEntity elasticTimeSearchRequestEntity) {
        String phoneNumber = elasticTimeSearchRequestEntity.getPhoneNumber();
        LocalDateTime startDateTime = elasticTimeSearchRequestEntity.getStartDateTime();
        LocalDateTime endDateTime = elasticTimeSearchRequestEntity.getEndDateTime();
        Integer page = elasticTimeSearchRequestEntity.getPage();
        Integer size = elasticTimeSearchRequestEntity.getSize();

        return elasticService.getMessagesBetweenTimeElastic(phoneNumber, startDateTime, endDateTime, page, size);

    }

}
