package com.example.movie_backend.dto.genre;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GenreDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties(value = "genres", allowSetters = true)
    private List<MovieDTO> movies;

    @Builder.Default
    private List<Long> movieIds = new ArrayList<>();
}
