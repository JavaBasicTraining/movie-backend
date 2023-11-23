package com.example.movie_backend.controller.interfaces;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IBaseController<T, I> {
    @PostMapping("create")
    ResponseEntity<T> create(@RequestBody T entity);

    @PostMapping("update")
    ResponseEntity<T> update(@RequestParam I id, @RequestBody T entity);

    @GetMapping("{id}/detail")
    ResponseEntity<T> getById(@PathVariable I id);

    @GetMapping("list")
    ResponseEntity<List<T>> getList();

    @GetMapping("page")
    ResponseEntity<Page<T>> getPage(@ParameterObject Pageable pageable);

    @DeleteMapping("{id}/delete")
    ResponseEntity<Boolean> deleteById(@PathVariable I id);
}
