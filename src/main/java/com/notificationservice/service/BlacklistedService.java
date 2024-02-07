package com.notificationservice.service;

import com.notificationservice.entity.Blacklisted;

import java.util.List;

public interface BlacklistedService {
    void addToBlacklistService(List<String> phoneNumbers);
    void removeFromBlacklistService(List<String> phoneNumbers);
    List<String> getALlBlacklistedNumbersService();
}
