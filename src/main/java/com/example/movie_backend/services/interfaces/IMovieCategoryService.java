package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.moviecategory.MovieCategoryDTO;
import com.example.movie_backend.dto.moviecategory.MovieCategoryDTO;

public interface IMovieCategoryService{

    MovieCategoryDTO create(MovieCategoryDTO dto);

    MovieCategoryDTO update(MovieCategoryDTO dto, Long id);

    MovieCategoryDTO getById(Long id);


    Boolean delete(Long id);
}
