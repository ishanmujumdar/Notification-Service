package com.notificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.cookie.SM;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "sms_elastic")
public class SMSElastic {

    @Id
    private long id;
    private String phoneNumber;
    private String message;
    private String status;
    private Integer failureCode;
    private String failureComments;
    private Long createdAt;
    private Long updatedAt;

    public SMSElastic(SMS sms) {
        this.id = sms.getId();
        this.phoneNumber = sms.getPhoneNumber();
        this.message = sms.getMessage();
        this.status = sms.getStatus();
        this.failureCode = sms.getFailureCode();
        this.failureComments = sms.getFailureComments();
        this.createdAt = sms.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.updatedAt = sms.getUpdatedAt().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}
