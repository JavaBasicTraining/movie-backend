package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IBaseController;
import com.example.movie_backend.services.interfaces.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<T, ID> implements IBaseController<T, ID> {
    private final IBaseService<T, ID> iBaseService;

    protected BaseController(IBaseService<T, ID> iBaseService) {
        this.iBaseService = iBaseService;
    }

    @Override
    public ResponseEntity<T> create(T entity) {
        return ResponseEntity.ok(iBaseService.create(entity));
    }

    @Override
    public ResponseEntity<T> update(ID id, T entity) {
        return ResponseEntity.ok(iBaseService.update(id, entity));
    }

    @Override
    public ResponseEntity<T> getById(ID id) {
        return ResponseEntity.ok(iBaseService.getById(id));
    }

    @Override
    public ResponseEntity<List<T>> getList() {
        return ResponseEntity.ok(iBaseService.getList());
    }

    @Override
    public ResponseEntity<Boolean> deleteById(ID id) {
        return ResponseEntity.ok(iBaseService.deleteById(id));
    }

    @Override
    public ResponseEntity<Page<T>> getPage(Integer number, Integer size) {
        return ResponseEntity.ok(iBaseService.getPage(number, size));
    }
}
