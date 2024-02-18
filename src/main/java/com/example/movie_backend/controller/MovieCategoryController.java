package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMovieCategoryController;
import com.example.movie_backend.dto.moviecategory.MovieCategoryDTO;
import com.example.movie_backend.services.MovieCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MovieCategoryController implements IMovieCategoryController {

    public final MovieCategoryService service;

    public MovieCategoryController(MovieCategoryService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<MovieCategoryDTO> create(MovieCategoryDTO movieCategoryDTO) {
        return ResponseEntity.ok(service.create(movieCategoryDTO));

    }

    @Override
    public ResponseEntity<MovieCategoryDTO> update(MovieCategoryDTO movieCategoryDTO, Long id) {
        return ResponseEntity.ok(service.update(movieCategoryDTO,id));

    }

    @Override
    public ResponseEntity<MovieCategoryDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @Override
    public boolean delete(Long id) {
         ResponseEntity.ok(service.delete(id));

        return true;
    }
}
