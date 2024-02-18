package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.ICategoryController;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController  implements ICategoryController {

    public final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<CategoryDTO> create(CategoryDTO category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @Override
    public ResponseEntity<CategoryDTO> update(CategoryDTO category, Long id) {
        return ResponseEntity.ok(categoryService.update(category,id));
    }

    @Override
    public ResponseEntity<CategoryDTO> getById(Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @Override
    public boolean delete(Long id) {
         ResponseEntity.ok(categoryService.delete(id));
         return true;
    }
}
