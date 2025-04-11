package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import com.example.movie_backend.entity.MoviePackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface MoviePackageMapper {
    @Mapping(target = "id", ignore = true)
    public MoviePackage toEntity(MoviePackageDTO dto);

    @Mapping(target = "id", ignore = true)
    public MoviePackage toEntity(MoviePackageDTO dto, Long id);

    public MoviePackageDTO toDTO(MoviePackage entity) ;
}
