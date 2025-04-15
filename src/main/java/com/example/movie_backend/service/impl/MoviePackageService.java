package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import com.example.movie_backend.mapper.MoviePackageMapper;
import com.example.movie_backend.entity.MoviePackage;
import com.example.movie_backend.repository.MoviePackageRepository;
import com.example.movie_backend.service.IMoviePackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoviePackageService implements IMoviePackageService {

    public final MoviePackageRepository repository;
    public final MoviePackageMapper moviePackageMapper;

    @Override
    public MoviePackageDTO create(MoviePackageDTO dto) {
        MoviePackage comment = moviePackageMapper.toEntity(dto);
        return moviePackageMapper.toDTO(repository.save(comment));
    }

    @Override
    public MoviePackageDTO update(MoviePackageDTO dto, Long id) {
        MoviePackage comment = moviePackageMapper.toEntity(dto, id);
        return moviePackageMapper.toDTO(repository.save(comment));
    }

    @Override
    public MoviePackageDTO getById(Long id) {
        return this.repository.findById(id)
            .map(this.moviePackageMapper::toDTO)
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
