package com.example.movie_backend.services;

import com.example.movie_backend.services.interfaces.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> implements IBaseService<T, ID> {
    private final JpaRepository<T, ID> repository;

    public BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(ID id, T entity) {
        if (getById(id) == null) {
            return null;
        }
        return repository.save(entity);
    }

    @Override
    public T getById(ID id) {
        Optional<T> optionalT = repository.findById(id);
        if (optionalT.isEmpty()) {
            return null;
        }
        return optionalT.get();
    }

    @Override
    public List<T> getList() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(ID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Page<T> getPage(Integer number, Integer size) {
        return repository.findAll(PageRequest.of(number, size));
    }
}
