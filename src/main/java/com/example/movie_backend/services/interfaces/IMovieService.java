package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Movie;

import java.util.UUID;

public interface IMovieService{
    MovieDTO create(MovieDTO dto);

    MovieDTO update(MovieDTO dto, Long id);

    MovieDTO getById(Long id);


    Boolean delete(Long id);
}
