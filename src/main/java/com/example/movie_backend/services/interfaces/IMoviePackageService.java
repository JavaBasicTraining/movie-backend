package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import com.example.movie_backend.entity.MoviePackage;

import java.util.UUID;

public interface IMoviePackageService  {

    MoviePackageDTO create(MoviePackageDTO dto);

    MoviePackageDTO update(MoviePackageDTO dto, Long id);

    MoviePackageDTO getById(Long id);


    Boolean delete(Long id);
}
