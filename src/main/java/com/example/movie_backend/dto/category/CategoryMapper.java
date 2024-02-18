package com.example.movie_backend.dto.category;

import com.example.movie_backend.entity.Category;
import org.springframework.stereotype.Component;
@Component
public class CategoryMapper {
    public Category toEntity (CategoryDTO dto)
    {
        return Category.builder()
                .name(dto.getName())
                .build();
    }
    public Category toEntity (CategoryDTO dto, Long id )
    {
        return Category.builder()
                .name(dto.getName())
                .build();
    }
    public CategoryDTO toDTO(Category entity) {
        return CategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }


}
