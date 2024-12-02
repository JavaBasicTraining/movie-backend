package com.example.movie_backend.controller;

import com.example.movie_backend.controller.dto.request.GetCategoriesFilter;
import com.example.movie_backend.dto.category.CategoryDTO;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/category")
public interface ICategoryController {
    @PostMapping
    ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO category);

    @PutMapping("{id}")
    ResponseEntity<CategoryDTO> update(@PathVariable("id") Long id, @RequestBody CategoryDTO category);

    @GetMapping("{id}")
    ResponseEntity<CategoryDTO> getById(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<CategoryDTO>> getListGenreByName(@ParameterObject GetCategoriesFilter filter);

    @DeleteMapping()
    boolean delete(@RequestParam Long id);
}
