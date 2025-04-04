package com.example.movie_backend.service.impl;


import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.controller.dto.request.GetCategoriesFilter;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.mapper.CategoryMapper;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.repository.CategoryRepository;
import com.example.movie_backend.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    public final CategoryMapper mapper;
    public final CategoryRepository repository;

    @Override
    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        Category category = mapper.toEntity(dto);
        category = repository.save(category);
        return mapper.toDTO(category);
    }

    @Override
    public CategoryDTO update(CategoryDTO entity, Long id) {
        Category category = mapper.toEntity(entity, id);
        return mapper.toDTO(repository.save(category));
    }

    @Override
    public CategoryDTO getById(Long id) {

        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }

    @Override
    public List<CategoryDTO> getList(GetCategoriesFilter filter) {
        return repository.filterCategory(filter.getSearchTerm(), filter.getExcludeIds()).stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
