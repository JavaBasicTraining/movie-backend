package com.example.movie_backend.model.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CreateMovieRequest {
    private String name;

    private String posterUrl;

    private String videoSourceUrl;

    private String viTitle;

    private String enTitle;

    private String description;
}
