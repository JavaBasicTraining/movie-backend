package com.example.movie_backend.service;

public interface IPresignedUrlCacheService {
    String getUrl(String object);

    void setUrl(String object, String url);
}
