package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.ICategoryController;
import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.category.CategoryMapper;
import com.example.movie_backend.repository.CategoryRepository;
import com.example.movie_backend.service.impl.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController implements ICategoryController {

    public final CategoryService categoryService;
    public final CategoryRepository repository;
    public final CategoryMapper mapper;


    @Override
    public ResponseEntity<CategoryDTO> create(CategoryDTO category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @Override
    public ResponseEntity<CategoryDTO> update(Long id, CategoryDTO category) {
        return ResponseEntity.ok(categoryService.update(category, id));
    }

    @Override
    public ResponseEntity<CategoryDTO> getById(Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getListGenreByName(GetCategoriesFilter filter) {
        return ResponseEntity.ok(categoryService.getList(filter));
    }

    @Override
    public boolean delete(Long id) {
        ResponseEntity.ok(categoryService.delete(id));
        return true;
    }

}
