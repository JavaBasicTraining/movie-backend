package com.example.movie_backend.dto.category;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDTO dto) {
        return Category.builder()
                .name(dto.getName())
                .movieSet(
                        dto.getMovieIds().stream()
                                .map(id -> Movie.builder()
                                                .id(id)
                                                .build()
                                )
                                .collect(Collectors.toSet())
                )
                .build();
    }

    public Category toEntity(CategoryDTO dto, Long id) {
        return Category.builder()
                .name(dto.getName())
                .movieSet(dto.getMovieSet().stream()
                        .map(movie -> Movie.builder()
                        .id(id)
                        .build()).collect(Collectors.toSet()))
                .build();
    }

    public CategoryDTO toDTO(Category entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }


}
