package com.notificationservice.service;

import com.notificationservice.entity.SMSElastic;
import org.joda.time.DateTime;
import java.time.LocalDateTime;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

public interface ElasticService {
    List<SMSElastic> searchTextElastic(String searchText, Integer page, Integer size);
    List<SMSElastic> getMessagesBetweenTimeElastic(String phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer page, Integer size);

}
