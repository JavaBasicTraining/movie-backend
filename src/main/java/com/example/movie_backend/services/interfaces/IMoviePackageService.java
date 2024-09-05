package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;

public interface IMoviePackageService {

    MoviePackageDTO create(MoviePackageDTO dto);

    MoviePackageDTO update(MoviePackageDTO dto, Long id);

    MoviePackageDTO getById(Long id);


    Boolean delete(Long id);
}
