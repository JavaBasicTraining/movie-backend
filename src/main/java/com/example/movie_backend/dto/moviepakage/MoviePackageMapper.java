package com.example.movie_backend.dto.moviepakage;

import com.example.movie_backend.entity.MoviePackage;
import org.springframework.stereotype.Component;

@Component
public class MoviePackageMapper {
    public MoviePackage toEntity (MoviePackageDTO dto)
    {
        return MoviePackage.builder()
                .type(dto.getType())
                .build();
    }

    public MoviePackage toEntity (MoviePackageDTO dto, Long id)
    {
        return MoviePackage.builder()
                .type(dto.getType())
                .build();
    }

    public MoviePackageDTO toDTO (MoviePackage entity)
    {
        return MoviePackageDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
    }
}
