package com.example.movie_backend.controller.impl;

import com.example.movie_backend.controller.IGenreController;
import com.example.movie_backend.controller.dto.request.GetCategoriesFilter;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.repository.GenreRepository;
import com.example.movie_backend.service.impl.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController implements IGenreController {

    public final GenreService categoryService;
    public final GenreRepository repository;

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
}
