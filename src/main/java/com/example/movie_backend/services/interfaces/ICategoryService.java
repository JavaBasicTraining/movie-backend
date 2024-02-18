package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.entity.Category;

import java.util.List;

public interface ICategoryService {

    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(CategoryDTO dto, Long id);

    CategoryDTO getById(Long id);

    Boolean delete(Long id);


}
