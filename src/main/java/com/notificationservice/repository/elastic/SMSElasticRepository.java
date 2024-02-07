package com.notificationservice.repository.elastic;

import com.notificationservice.entity.SMSElastic;
import org.joda.time.DateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

@Repository
public interface SMSElasticRepository extends ElasticsearchRepository<SMSElastic, Long> {

    Page<SMSElastic> findByPhoneNumberAndCreatedAtBetween(String phoneNumber, Long startTime, Long endTime, Pageable pageable);
    Page<SMSElastic> findByMessageContaining(String searchText, Pageable pageable);

//    Page<SMSElastic> findByPhoneNumberAndCreatedAtBetween(String phoneNumber, Date startTime, Date endTime);
//    Page<SMSElastic> findByMessageContaining(String searchText);
    SMSElastic save(SMSElastic entity);

}