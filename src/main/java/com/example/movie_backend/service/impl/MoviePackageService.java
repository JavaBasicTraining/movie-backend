package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import com.example.movie_backend.mapper.MoviePackageMapper;
import com.example.movie_backend.entity.MoviePackage;
import com.example.movie_backend.repository.MoviePackageRepository;
import com.example.movie_backend.service.IMoviePackageService;
import org.springframework.stereotype.Service;

@Service
public class MoviePackageService implements IMoviePackageService {

    public final MoviePackageRepository repository;
    public final MoviePackageMapper mapper;
    public MoviePackageService(MoviePackageRepository repository, MoviePackageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MoviePackageDTO create(MoviePackageDTO dto) {
        MoviePackage comment = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MoviePackageDTO update(MoviePackageDTO dto, Long id) {
        MoviePackage comment = mapper.toEntity(dto, id);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MoviePackageDTO getById(Long id) {
        return this.repository.findById(id)
            .map(this.mapper::toDTO)
            .orElseThrow(
                () -> new BadRequestException("Movie not found")
            );
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
