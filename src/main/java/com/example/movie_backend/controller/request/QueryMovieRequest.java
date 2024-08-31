package com.example.movie_backend.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryMovieRequest {
    private String keyword;
    private String genre;
    private String country;
}
