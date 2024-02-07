package com.notificationservice;

import com.notificationservice.entity.SMSElastic;
import com.notificationservice.repository.elastic.SMSElasticRepository;
import com.notificationservice.service.impl.ElasticServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ElasticServiceTests {
    @Mock
    private SMSElasticRepository smsElasticRepository;

    @InjectMocks
    private ElasticServiceImpl elasticService;

    @Test
    void searchTextElasticTest () {
        Mockito.when(smsElasticRepository.findByMessageContaining((Mockito.anyString()), Mockito.any(Pageable.class))).thenReturn(getMockPages());
        List<SMSElastic> smsResults = elasticService.searchTextElastic("Any", 0, 2);
        Assert.assertEquals(smsResults.size(), 1);
    }

    @Test
    void getMessagesBetweenTimeElasticTest () {
        Pageable pageable = PageRequest.of(0, 2);
        Mockito.when(smsElasticRepository.findByPhoneNumberAndCreatedAtBetween((Mockito.anyString()), Mockito.anyLong(), Mockito.anyLong(), Mockito.any(Pageable.class))).thenReturn(getMockPages());
        List<SMSElastic> smsResults = elasticService.getMessagesBetweenTimeElastic("123456", LocalDateTime.now(), LocalDateTime.now(), 0, 2);
        Assert.assertEquals(smsResults.size(), 1);
    }

    Page<SMSElastic> getMockPages() {

        Pageable pageable = PageRequest.of(0, 5);

        SMSElastic smsElastic = new SMSElastic();
        smsElastic.setId(1);
        smsElastic.setPhoneNumber("1234567");
        smsElastic.setStatus("Pending");

        List<SMSElastic> smsElasticList = new ArrayList<>();
        smsElasticList.add(smsElastic);

        Page<SMSElastic> smsElasticPage = new PageImpl<>(smsElasticList);

        return smsElasticPage;
    }
}
