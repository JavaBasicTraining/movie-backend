package com.example.movie_backend.dto.moviecategory;

import com.example.movie_backend.entity.MovieCategory;
import org.springframework.stereotype.Component;

@Component
public class MovieCategoryMapper {

    public MovieCategory toEntity (MovieCategoryDTO dto)
    {
        return MovieCategory.builder()
                .movieId(dto.getMovieId())
                .categoryId(dto.getCategoryId())
                .build();
    }

    public MovieCategory toEntity (MovieCategoryDTO dto, Long id)
    {
        return MovieCategory.builder()
                .movieId(dto.getMovieId())
                .categoryId(dto.getCategoryId())
                .build();
    }

    public MovieCategoryDTO toDTO (MovieCategory entity)
    {
        return MovieCategoryDTO.builder()
                .id(entity.getId())
                .movieId(entity.getMovieId())
                .categoryId(entity.getCategoryId())
                .build();
    }
}
