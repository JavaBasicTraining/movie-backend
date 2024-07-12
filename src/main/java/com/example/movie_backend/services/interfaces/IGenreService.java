package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.genre.GenreDTO;

import java.util.List;

public interface IGenreService {

    GenreDTO create(GenreDTO dto);

    GenreDTO update(GenreDTO dto, Long id);

    GenreDTO getById(Long id);

    List<GenreDTO> getList(GetCategoriesFilter filter);

    Boolean delete(Long id);


}
