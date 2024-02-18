package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.moviecategory.MovieCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/movie-category/")
public interface IMovieCategoryController {
    @PostMapping
    ResponseEntity<MovieCategoryDTO> create(@RequestBody MovieCategoryDTO movieCategoryDTO);

    @PutMapping
    ResponseEntity<MovieCategoryDTO> update(@RequestBody MovieCategoryDTO movieCategoryDTO, @RequestParam Long id);

    @GetMapping("{id}")
    ResponseEntity<MovieCategoryDTO> getById(@RequestParam Long id);
    @DeleteMapping("{id}")
    boolean delete(@RequestParam Long id);
}
