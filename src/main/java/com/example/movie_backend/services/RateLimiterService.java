package com.example.movie_backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String, UserRateLimit> ipLimits = new ConcurrentHashMap<>();

    @Value("${rate.limit.max-requests}")
    private int maxRequests;
    @Value("${rate.limit.time-window}")
    private int timeWindow;

    @Data
    @AllArgsConstructor
    private static class UserRateLimit {
        private int requestCount;
        private long lastResetTime;
    }

    public boolean allowRequest(String ipAddress) {
        long currentTime = System.currentTimeMillis();

        UserRateLimit limit = ipLimits.computeIfAbsent(ipAddress,
                k -> new UserRateLimit(0, currentTime));

        if (currentTime - limit.getLastResetTime() > timeWindow) {
            limit.setRequestCount(0);
            limit.setLastResetTime(currentTime);
        }

        if (limit.getRequestCount() >= maxRequests) {
            return false;
        }

        limit.setRequestCount(limit.getRequestCount() + 1);
        return true;
    }
}
