package com.notificationservice.entity.RequestEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElasticTextSearchRequestEntity {
    String text;
    Integer page;
    Integer size;
}
