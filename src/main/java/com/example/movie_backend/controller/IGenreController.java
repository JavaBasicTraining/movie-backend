package com.example.movie_backend.controller;

import com.example.movie_backend.controller.dto.request.GetCategoriesFilter;
import com.example.movie_backend.dto.genre.GenreDTO;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/genre")
public interface IGenreController {
    @PostMapping
    ResponseEntity<GenreDTO> create(@RequestBody GenreDTO category);

    @PutMapping("{id}")
    ResponseEntity<GenreDTO> update(@PathVariable("id") Long id, @RequestBody GenreDTO category);

    @GetMapping("{id}")
    ResponseEntity<GenreDTO> getById(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<GenreDTO>> getListGenreByName(@ParameterObject GetCategoriesFilter filter);

    @DeleteMapping()
    boolean delete(@RequestParam Long id);
}
