package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.Genre;
import org.springframework.stereotype.Component;

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
                .build();
    }

    public GenreDTO toDTO(Genre entity) {
        return GenreDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
