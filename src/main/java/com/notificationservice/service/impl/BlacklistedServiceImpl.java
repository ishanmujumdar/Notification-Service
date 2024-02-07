package com.notificationservice.service.impl;

import com.notificationservice.entity.Blacklisted;
import com.notificationservice.repository.sql.BlacklistedRepository;
import com.notificationservice.service.BlacklistedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class BlacklistedServiceImpl implements BlacklistedService {

    @Autowired
    private BlacklistedRepository blacklistedRepository;

    @Override
    public void addToBlacklistService(List<String> phoneNumbers) {
        List<Blacklisted> blacklisteds = new ArrayList<>();
        for(int i = 0; i < phoneNumbers.size(); i++) {
            Blacklisted blacklist = new Blacklisted();
            blacklist.setPhoneNumber(phoneNumbers.get(i));
            blacklisteds.add(blacklist);
        }
        blacklistedRepository.saveAll(blacklisteds);
    }

    @Override
    public void removeFromBlacklistService(List<String> phoneNumbers) {
        List<Blacklisted> blacklisteds = new ArrayList<>();
        for(int i = 0; i < phoneNumbers.size(); i++) {
            Blacklisted blacklist = blacklistedRepository.getReferenceById(phoneNumbers.get(i));
            blacklisteds.add(blacklist);
        }
        blacklistedRepository.deleteAll(blacklisteds);
    }

    @Override
    public List<String> getALlBlacklistedNumbersService() {
        List<Blacklisted> blacklisteds = blacklistedRepository.findAll();
        List<String> listOfBlacklistedNumbers = new ArrayList<>();
        for(int i = 0; i < blacklisteds.size(); i++) {
            Blacklisted blacklisted = blacklisteds.get(i);
            listOfBlacklistedNumbers.add(blacklisted.getPhoneNumber());
        }
        return listOfBlacklistedNumbers;
    }
}
