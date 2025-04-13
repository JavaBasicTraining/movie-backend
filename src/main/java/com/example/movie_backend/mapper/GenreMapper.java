package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Genre toEntity(GenreDTO dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)
    Genre toEntity(GenreDTO dto, Long id);


    GenreDTO toDTO(Genre entity);
}