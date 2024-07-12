package com.example.movie_backend.dto.category;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CategoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Movie> movies;

    @Builder.Default
    private Set<Long> movieIds = new HashSet<>();
}
