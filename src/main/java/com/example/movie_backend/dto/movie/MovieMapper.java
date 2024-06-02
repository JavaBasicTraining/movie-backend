package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public Movie toEntity(MovieDTO dto) {

        return Movie.builder()
                .name(dto.getName())
                .posterUrl(dto.getPosterUrl())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .categorySet(dto.getIds().stream().map(id -> Category.builder()
                        .id(id)
                        .build()).collect(Collectors.toSet()))
                .build();
    }


    public Movie toEntity(MovieDTO dto, Long id) {
        return Movie.builder()
                .id(dto.getId())
                .name(dto.getName())
                .posterUrl(dto.getPosterUrl())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .categorySet(dto.getIds().stream()
                        .map(ids-> Category.builder()
                        .id(ids)
                        .build()).collect(Collectors.toSet()))
                .build();
    }

    public MovieDTO toDTO(Movie entity) {
        // ví dụ từ set entity map sang set dto
        // entity.getCategorySet() này là set category entity
        //  Set<Category> =>  Set<CategoryDTO>
        return MovieDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .description(entity.getDescription())
                .videoMinioPath(entity.getVideoMinioPath())
                .categoryDTOSet(entity.getCategorySet().stream().map(category -> CategoryDTO.builder()
                        .id(category.getId())
                        .name(category.getName())

                        .build()).collect(Collectors.toSet()) )
                .build();
    }

    public MovieDTOWithoutJoin toDTOWithoutJoin(Movie entity) {
        return MovieDTOWithoutJoin.builder()
                .id(entity.getId())
                .name(entity.getName())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .description(entity.getDescription())
                .build();
    }
}