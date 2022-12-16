package com.example.movie_backend.services.interfaces;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IBaseService<T, ID> {
    T create(T entity);

    T update(ID id, T entity);

    T getById(ID id);

    List<T> getList();

    Page<T> getPage(Integer number, Integer size);

    boolean deleteById(ID id);
}
