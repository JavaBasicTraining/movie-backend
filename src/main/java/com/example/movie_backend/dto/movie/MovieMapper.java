package com.example.movie_backend.dto.movie;

import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie toEntity(MovieDTO dto) {
        return Movie.builder()
                .name(dto.getName())
                .posterUrl(dto.getPosterUrl())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .moviePackageId(dto.getMoviePackageId())
                .build();
    }


    public Movie toEntity(MovieDTO dto, Long id) {
        return Movie.builder()
                .name(dto.getName())
                .posterUrl(dto.getPosterUrl())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .moviePackageId(dto.getMoviePackageId())
                .build();
    }

    public MovieDTO toDTO(Movie entity) {
        return MovieDTO.builder()
                .name(entity.getName())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .description(entity.getDescription())
                .moviePackageId(entity.getMoviePackageId())
                .build();
    }
}


