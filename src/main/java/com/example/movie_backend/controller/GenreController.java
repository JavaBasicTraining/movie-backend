package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IGenreController;
import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.dto.genre.GenreMapper;
import com.example.movie_backend.repository.GenreRepository;
import com.example.movie_backend.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController implements IGenreController {

    public final GenreService categoryService;
    public final GenreRepository repository;
    public final GenreMapper mapper;


    public GenreController(GenreService categoryService, GenreRepository repository, GenreMapper mapper) {
        this.categoryService = categoryService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<GenreDTO> create(GenreDTO category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @Override
    public ResponseEntity<GenreDTO> update(Long id, GenreDTO category) {
        return ResponseEntity.ok(categoryService.update(category, id));
    }

    @Override
    public ResponseEntity<GenreDTO> getById(Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @Override
    public ResponseEntity<List<GenreDTO>> getListGenreByName(GetCategoriesFilter filter) {
        return ResponseEntity.ok(categoryService.getList(filter));
    }


    @Override
    public boolean delete(Long id) {
        ResponseEntity.ok(categoryService.delete(id));
        return true;
    }

//    @GetMapping(path = {"/filter", "/filter/{categoryName}"})
//    public ResponseEntity<Set<CategoryDTO>> filter(@PathVariable(required = false) String categoryName) {
//        return ResponseEntity.ok(categoryService.filter(categoryName));
//    }

}
