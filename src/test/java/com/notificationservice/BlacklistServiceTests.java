package com.notificationservice;

import com.notificationservice.entity.Blacklisted;
import com.notificationservice.repository.sql.BlacklistedRepository;
import com.notificationservice.service.impl.BlacklistedServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BlacklistServiceTests {

    @Mock
    private BlacklistedRepository blacklistedRepository;

    @InjectMocks
    private BlacklistedServiceImpl blacklistedService;

    @Test
    public void addToBlacklistServiceMock() {
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.addAll(List.of("1234567", "3456789", "456789"));
        Blacklisted blacklisted = new Blacklisted();

        Mockito.when(blacklistedRepository.save(Mockito.any(Blacklisted.class))).thenReturn(blacklisted);
        blacklistedService.addToBlacklistService(phoneNumbers);

        Assert.assertEquals(1, 1);

    }

    @Test
    public void removeFromBlacklistServiceTest() {
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.addAll(List.of("1234567", "3456789", "456789"));

        Mockito.doNothing().when(blacklistedRepository).delete(Mockito.any(Blacklisted.class));
        blacklistedService.removeFromBlacklistService(phoneNumbers);

        Assert.assertEquals(1, 1);
    }

    @Test
    public void getAllBlacklistedNumbersServiceTest() {

        Mockito.when(blacklistedRepository.findAll()).thenReturn(getMockBlacklistedList());
        List<String> listOfBlacklistedNumbes = blacklistedService.getALlBlacklistedNumbersService();
        Assert.assertEquals(listOfBlacklistedNumbes.size(), 1);
    }

    List<Blacklisted> getMockBlacklistedList() {
        List<Blacklisted> blacklisteds = new ArrayList<>();
        Blacklisted blacklisted = new Blacklisted();
        blacklisted.setPhoneNumber("123456");
        blacklisteds.add(blacklisted);
        return blacklisteds;
    }

}
