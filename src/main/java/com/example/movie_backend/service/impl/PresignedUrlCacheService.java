package com.example.movie_backend.service.impl;

import com.example.movie_backend.dto.CachedUrl;
import com.example.movie_backend.service.IPresignedUrlCacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class PresignedUrlCacheService implements IPresignedUrlCacheService {
    private final ConcurrentHashMap<String, CachedUrl> cachedUrls = new ConcurrentHashMap<>();

    public String getUrl(String object) {
        long currentTime = System.currentTimeMillis();
        CachedUrl cachedUrl = cachedUrls.get(object);
        if (cachedUrl != null && currentTime < cachedUrl.getExpiryTime()) {
            return cachedUrl.getUrl();
        }
        return null;
    }

    @Override
    public void setUrl(String object, String url) {
        long expiryTime = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2);
        cachedUrls.put(object, new CachedUrl(url, expiryTime));
    }
}
