package com.example.movie_backend.minio.enumrate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MediaType {
    IMAGE("images"),
    VIDEO("videos"),
    FILE("files"),
    ;

    private final String path;

}