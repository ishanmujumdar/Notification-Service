package com.notificationservice.service.impl;


import com.notificationservice.entity.SMSElastic;
import com.notificationservice.repository.elastic.SMSElasticRepository;
import com.notificationservice.service.ElasticService;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class ElasticServiceImpl implements ElasticService {

    @Autowired
    private SMSElasticRepository smsElasticRepository;

    @Override
    public List<SMSElastic> searchTextElastic(String searchText, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SMSElastic> pageResults = smsElasticRepository.findByMessageContaining(searchText, pageable);
        List<SMSElastic> smsResults = new ArrayList<>();
        pageResults.forEach(entity -> smsResults.add(entity));
        return smsResults;
    }

    @Override
    public List<SMSElastic> getMessagesBetweenTimeElastic(String phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Long startDateTimeLong = startDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        Long endDateTimeLong = endDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        Page<SMSElastic> pageResults = smsElasticRepository.findByPhoneNumberAndCreatedAtBetween(phoneNumber, startDateTimeLong, endDateTimeLong, pageable);
        List<SMSElastic> smsResults = new ArrayList<>();
        pageResults.forEach(entity -> smsResults.add(entity));
        return smsResults;

    }

}
