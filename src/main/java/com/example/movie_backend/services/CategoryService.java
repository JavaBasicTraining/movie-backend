package com.example.movie_backend.services;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.category.CategoryMapper;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.repository.CategoryRepository;
import com.example.movie_backend.services.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    public final CategoryMapper mapper;
    public  final CategoryRepository repository;

    public CategoryService(CategoryMapper mapper, CategoryRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public CategoryDTO create(CategoryDTO entity) {
        Category category = mapper.toEntity(entity);
        return mapper.toDTO(repository.save(category));
    }

    @Override
    public CategoryDTO update(CategoryDTO entity, Long id) {
        Category category = mapper.toEntity(entity,id);
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
    public Boolean delete(Long id) {
        return null;
    }


}
