package com.example.movie_backend.dto.genre;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class NameGenreRequest {

    private String name;
}
