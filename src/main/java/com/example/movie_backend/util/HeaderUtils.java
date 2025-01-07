package com.example.movie_backend.util;

import org.springframework.http.HttpHeaders;

public class HeaderUtils {
    public static final String X_TOTAL_PAGES = "x-total-pages";

    HeaderUtils() {
    }

    public static HttpHeaders buildTotalSizeHeader(long totalSize) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_PAGES, String.valueOf(totalSize));
        return headers;
    }
}
