package com.example.movie_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CachedUrl {
    private String url;
    private long expiryTime;

    public CachedUrl(String url, long expiryTime) {
        this.url = url;
        this.expiryTime = expiryTime;
    }
}
