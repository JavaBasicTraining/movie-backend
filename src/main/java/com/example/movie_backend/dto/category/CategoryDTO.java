package com.example.movie_backend.dto.category;

import com.example.movie_backend.dto.movie.MovieDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CategoryDTO {
    private Long id;

    private String name;

    private List<MovieDTO> movies;
}
