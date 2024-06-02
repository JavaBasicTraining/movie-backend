package com.example.movie_backend.dto.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class QueryMovieResponse {

    @Builder.Default
    private List<MovieDTOWithoutJoin> movies = new ArrayList<>();
}
