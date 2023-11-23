package com.example.movie_backend.services;

import com.example.movie_backend.services.interfaces.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, I> implements IBaseService<T, I> {
    private final JpaRepository<T, I> repository;

    protected BaseService(JpaRepository<T, I> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(I id, T entity) {
        if (getById(id) == null) {
            return null;
        }
        return repository.save(entity);
    }

    @Override
    public T getById(I id) {
        Optional<T> optionalT = repository.findById(id);
        return optionalT.orElse(null);
    }

    @Override
    public List<T> getList() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(I id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
