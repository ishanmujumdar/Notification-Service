package com.notificationservice.handler;

import com.notificationservice.entity.RequestEntities.BlacklistRequestEntity;
import com.notificationservice.service.BlacklistedService;
import com.notificationservice.service.RedisServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlacklistRequestHandler {
    @Autowired
    private BlacklistedService blacklistedService;

    @Autowired
    private RedisServices redisServices;

    public void addToBlacklistHandler (BlacklistRequestEntity blacklistRequestEntity) {
        List<String> phoneNumbers = blacklistRequestEntity.getPhoneNumbers();
        blacklistedService.addToBlacklistService(phoneNumbers);
        redisServices.addToBlacklistCache(phoneNumbers);
    }

    public void removeFromBlacklistHandler(BlacklistRequestEntity blacklistRequestEntity) {
        List<String> phoneNumbers = blacklistRequestEntity.getPhoneNumbers();
        blacklistedService.removeFromBlacklistService(phoneNumbers);
        redisServices.removeFromBlacklistCache(phoneNumbers);
    }

    public List<String> getAllBlacklistedNumbersHandle() {
        List<String> blackListedPhonenumbers = blacklistedService.getALlBlacklistedNumbersService();
        return blackListedPhonenumbers;
    }

}
