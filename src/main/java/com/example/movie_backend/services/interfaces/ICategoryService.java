package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.category.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(CategoryDTO dto, Long id);

    CategoryDTO getById(Long id);

    List<CategoryDTO> getList(GetCategoriesFilter filter);

    Boolean delete(Long id);


}
