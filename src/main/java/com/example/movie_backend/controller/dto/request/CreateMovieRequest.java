package com.example.movie_backend.controller.dto.request;

import com.example.movie_backend.dto.movie.MovieDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMovieRequest extends MovieDTO {
    private Long categoryId;
    private List<Long> genreIds;
}
