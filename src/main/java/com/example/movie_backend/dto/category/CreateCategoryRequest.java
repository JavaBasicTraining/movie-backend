package com.example.movie_backend.dto.category;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CreateCategoryRequest {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private List<Long> movieId;
}
