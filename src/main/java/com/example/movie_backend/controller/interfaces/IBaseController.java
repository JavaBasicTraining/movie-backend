package com.example.movie_backend.controller.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IBaseController<T, ID> {
    @PostMapping("create")
    ResponseEntity<T> create(@RequestBody T entity);

    @PostMapping("update")
    ResponseEntity<T> update(@RequestParam ID id, @RequestBody T entity);

    @GetMapping("{id}/detail")
    ResponseEntity<T> getById(@PathVariable ID id);

    @GetMapping("list")
    ResponseEntity<List<T>> getList();

    @GetMapping("page")
    ResponseEntity<Page<T>> getPage(@RequestParam Integer number, @RequestParam Integer size);

    @DeleteMapping("{id}/delete")
    ResponseEntity<Boolean> deleteById(@PathVariable ID id);
}
