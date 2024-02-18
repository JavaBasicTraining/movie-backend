package com.example.movie_backend.services;

import com.example.movie_backend.dto.moviecategory.MovieCategoryDTO;
import com.example.movie_backend.dto.moviecategory.MovieCategoryMapper;
import com.example.movie_backend.entity.MovieCategory;
import com.example.movie_backend.repository.MovieCategoryRepository;
import com.example.movie_backend.services.interfaces.IMovieCategoryService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

@Service
public class MovieCategoryService implements IMovieCategoryService {

    public  final MovieCategoryRepository repository;

    public final MovieCategoryMapper mapper;

    public MovieCategoryService(MovieCategoryRepository repository, MovieCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public MovieCategoryDTO create(MovieCategoryDTO dto) {
        MovieCategory comment = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MovieCategoryDTO update(MovieCategoryDTO dto, Long id) {
        MovieCategory comment = mapper.toEntity(dto,id);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MovieCategoryDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
