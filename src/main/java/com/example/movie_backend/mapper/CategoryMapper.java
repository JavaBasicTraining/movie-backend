package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movies", ignore = true)

    Category toEntity(CategoryDTO dto) ;

    @Mapping(target = "id", ignore = true)
     @Mapping(target = "movies", ignore = true)
    Category toEntity(CategoryDTO dto, Long id) ;

    @Mapping(target = "movies", ignore = true)
    CategoryDTO toDTO(Category entity) ;
}
