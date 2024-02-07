package com.notificationservice.service.impl;

import com.notificationservice.service.RedisServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class RedisServicesImpl implements RedisServices {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addToBlacklistCache(List<String> phoneNumbers) {
        for (int i = 0; i < phoneNumbers.size(); i++) {
            String phoneNumber = phoneNumbers.get(i);
            redisTemplate.opsForSet().add("blacklisted", phoneNumber);
        }
    }

    @Override
    public void removeFromBlacklistCache(List<String> phoneNumbers) {
        for (int i = 0; i < phoneNumbers.size(); i++) {
            String phoneNumber = phoneNumbers.get(i);
            redisTemplate.opsForSet().remove("blacklisted", phoneNumber);
        }
    }
}
