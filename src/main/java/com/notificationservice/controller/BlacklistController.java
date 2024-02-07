package com.notificationservice.controller;

import com.notificationservice.handler.BlacklistRequestHandler;
import com.notificationservice.entity.RequestEntities.BlacklistRequestEntity;
import com.notificationservice.entity.ResponseEntities.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class BlacklistController {

    @Autowired
    private BlacklistRequestHandler blacklistRequestHandler;

    @PostMapping("/blacklist")
    public GenericResponse<String> addToBlacklist(@RequestBody BlacklistRequestEntity blacklistRequestEntity) {
        try {
            blacklistRequestHandler.addToBlacklistHandler(blacklistRequestEntity);
            return GenericResponse.<String>builder().success(true).data("Successfully blacklisted").build();
        } catch (Exception e) {
            return GenericResponse.<String>builder().success(false).error("Unable to add to blacklist").build();
        }

    }

    @DeleteMapping("/blacklist")
    public GenericResponse<String> removeFromBlacklist(@RequestBody BlacklistRequestEntity blacklistRequestEntity) {
        try {
            blacklistRequestHandler.removeFromBlacklistHandler(blacklistRequestEntity);
            return GenericResponse.<String>builder().success(true).data("Successfully whitelisted").build();
        } catch (Exception e) {
            return GenericResponse.<String>builder().success(true).error("Unable to whitelist").build();
        }

    }

    @GetMapping("/blacklist")
    public GenericResponse<List<String>> getALlBlacklistedNumbers() {
        try {
            List<String> blacklistedPhonenumbers = blacklistRequestHandler.getAllBlacklistedNumbersHandle();
            return GenericResponse.<List<String>>builder().success(true).data(blacklistedPhonenumbers).build();
        } catch (Exception e) {
            return GenericResponse.<List<String>>builder().success(false).error("Unable to fetch blacklisted numbers").build();
        }
    }
}
