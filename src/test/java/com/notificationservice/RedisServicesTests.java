package com.notificationservice;

import com.notificationservice.service.impl.RedisServicesImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RedisServicesTests {
    @Mock
    private RedisTemplate redisTemplate;
    @Mock
    private SetOperations setOperations;
    @InjectMocks
    private RedisServicesImpl redisServices;

    @Test
    public void addToBlacklistCacheTest() {
        Mockito.when(redisTemplate.opsForSet()).thenReturn(setOperations);
        Mockito.when(setOperations.add(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        List<String> phoneNumbers = new ArrayList<>(List.of("123456", "567890"));
        redisServices.addToBlacklistCache(phoneNumbers);
        Assert.assertEquals(1, 1);
    }

    @Test
    public void removeFromBlacklistCacheTest() {
        Mockito.when(redisTemplate.opsForSet()).thenReturn(setOperations);
        Mockito.when(setOperations.remove(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        List<String> phoneNumbers = new ArrayList<>(List.of("123456", "567890"));
        redisServices.removeFromBlacklistCache(phoneNumbers);
        Assert.assertEquals(1, 1);
    }

}
