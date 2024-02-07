package com.notificationservice.entity.RequestEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElasticTimeSearchRequestEntity {
    String phoneNumber;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    Integer page;
    Integer size;
}
