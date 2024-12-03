package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.Genre;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {
    public Genre toEntity(GenreDTO dto) {
        return Genre.builder()
                .name(dto.getName())
                .build();
    }

    public Genre toEntity(GenreDTO dto, Long id) {
        return Genre.builder()
                .name(dto.getName())
                .movies(dto.getMovies().stream()
                        .map(movie -> Movie.builder()
                                .id(id)
                                .build()).collect(Collectors.toSet()))
                .build();
    }

    public GenreDTO toDTO(Genre entity) {
        List<Long> movieIds = entity.getMovies().stream()
                .map(Movie::getId)
                .toList();

        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .movieIds(movieIds)
                .build();
    }
}
