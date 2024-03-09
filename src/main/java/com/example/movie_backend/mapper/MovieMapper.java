package com.example.movie_backend.mapper;

import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.model.movie.CreateMovieRequest;
import com.example.movie_backend.model.movie.MovieDTO;
import com.example.movie_backend.model.movie.UpdateMovieRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MovieMapper {

    public MovieDTO toDTO(Movie movie) {
        return MovieDTO.builder()
            .id(movie.getId())
            .name(movie.getName())
            .enTitle(movie.getEnTitle())
            .viTitle(movie.getViTitle())
            .videoSourceUrl(movie.getVideoSourceUrl())
            .posterUrl(movie.getPosterUrl())
            .description(movie.getDescription())
            .build();
    }

    public Movie fromCreateRequest(CreateMovieRequest request) {
        return Movie.builder()
            .name(request.getName())
            .enTitle(request.getEnTitle())
            .viTitle(request.getViTitle())
            .posterUrl(request.getPosterUrl())
            .videoSourceUrl(request.getVideoSourceUrl())
            .description(request.getDescription())
            .build();
    }

    public Movie fromUpdateRequest(UUID id, UpdateMovieRequest request) {
        return Movie.builder()
            .id(id)
            .name(request.getName())
            .enTitle(request.getEnTitle())
            .viTitle(request.getViTitle())
            .posterUrl(request.getPosterUrl())
            .videoSourceUrl(request.getVideoSourceUrl())
            .description(request.getDescription())
            .build();
    }
}
