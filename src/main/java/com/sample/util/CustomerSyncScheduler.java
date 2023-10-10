package com.sample.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.service.CustomerSyncService;

@Component
public class CustomerSyncScheduler {

    private final CustomerSyncService customerSyncService;

    public CustomerSyncScheduler(CustomerSyncService customerSyncService) {
        this.customerSyncService = customerSyncService;
    }

    @Scheduled(fixedDelay = 3600000) // Run every hour (adjust as needed)
    public void syncDataScheduled() {
        customerSyncService.syncData();
    }
}
