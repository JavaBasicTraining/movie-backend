package com.example.movie_backend.services;


import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.category.CategoryMapper;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.repository.CategoryRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    public final CategoryMapper mapper;
    public final CategoryRepository repository;
    public final MovieRepository movieRepository;

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
            .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
