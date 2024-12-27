package com.example.movie_backend.controller.dto.request;

import com.example.movie_backend.enumerate.FilerMovieType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryMovieRequest {
    private String keyword;
    private String genre;
    private String country;
    private FilerMovieType filter;
}
