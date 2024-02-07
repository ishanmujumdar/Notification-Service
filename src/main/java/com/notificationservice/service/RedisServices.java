package com.notificationservice.service;

import java.util.List;

public interface RedisServices {
    void addToBlacklistCache(List<String> phoneNumbers);
    void removeFromBlacklistCache(List<String> phoneNumbers);
}
