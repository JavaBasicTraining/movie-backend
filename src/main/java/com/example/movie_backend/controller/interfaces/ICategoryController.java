package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.category.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/category")
public interface ICategoryController {
    @PostMapping("create")
    ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO category);

    @PutMapping("update")
    ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO category, @RequestParam Long id);

    @GetMapping("getById")
    ResponseEntity<CategoryDTO> getById(@RequestParam Long id);
    @DeleteMapping()
    boolean delete(@RequestParam Long id);
}
