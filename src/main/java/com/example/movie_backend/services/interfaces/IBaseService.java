package com.example.movie_backend.services.interfaces;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IBaseService<T, I> {
    T create(T entity);

    T update(I id, T entity);

    T getById(I id);

    List<T> getList();

    Page<T> getPage(Integer number, Integer size);

    boolean deleteById(I id);
}
