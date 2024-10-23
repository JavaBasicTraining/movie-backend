package com.example.movie_backend.dto.genre;

import com.example.movie_backend.entity.Genre;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

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
        return GenreDTO.builder()
            .id(entity.getId())
            .name(entity.getName())
            .movieIds(entity.getMovies().stream()
                .map(
                    item -> item.getId()
                ).collect(Collectors.toSet()))
            .build();
    }
}
