package com.example.movie_backend.dto.movie;

import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public Movie toEntity(MovieDTO dto) {
        return Movie.builder()
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .categories(dto.getIdCategory() == null ? null : dto.getIdCategory()
                        .stream()
                        .map(ids -> Category.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))

                .build();
    }



    public Movie toEntity(MovieDTO dto, Long id) {
        return Movie.builder()
                .id(id)
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .description(dto.getDescription())
                .categories(dto.getIdCategory() == null ? null : dto.getIdCategory().stream()
                        .map(ids -> Category.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))

                .build();
    }

    public Movie toEntity(MovieDTO dto, String name) {
        return Movie.builder()

                .nameMovie(name)
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .description(dto.getDescription())
                .categories(dto.getIdCategory() == null ? null : dto.getIdCategory().stream()
                        .map(ids -> Category.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))

                .build();
    }

    public MovieDTO toDTO(Movie entity) {
        return MovieDTO.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .posterUrl(entity.getPosterUrl())
                .videoUrl(entity.getVideoUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .description(entity.getDescription())
                .idCategory(entity.getCategories() == null ? null : entity.getCategories().stream()
                        .map(item -> item.getId()).filter(id-> id!=  null)
                        .collect(Collectors.toSet()))

                .build();
    }

    public MovieDTOWithoutJoin toDTOWithoutJoin(Movie entity) {
        return MovieDTOWithoutJoin.builder()
                .nameMovie(entity.getNameMovie())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .description(entity.getDescription())
                .build();
    }
}