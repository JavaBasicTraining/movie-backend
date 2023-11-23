package com.example.movie_backend.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBaseService<T, I> {
    T create(T entity);

    T update(I id, T entity);

    T getById(I id);

    List<T> getList();

    Page<T> getPage(Pageable pageable);

    boolean deleteById(I id);
}
