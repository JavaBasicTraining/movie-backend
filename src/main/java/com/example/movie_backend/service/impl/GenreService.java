package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.dto.request.GetCategoriesFilter;
import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.Genre;
import com.example.movie_backend.mapper.GenreMapper;
import com.example.movie_backend.repository.GenreRepository;
import com.example.movie_backend.service.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService implements IGenreService {

    public final GenreMapper genreMapper;
    public final GenreRepository repository;

    @Override
    @Transactional
    public GenreDTO create(GenreDTO dto) {
        Genre genre = genreMapper.toEntity(dto);
        genre = repository.save(genre);
        return genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO update(GenreDTO entity, Long id) {
        Genre category = genreMapper.toEntity(entity, id);
        return genreMapper.toDTO(repository.save(category));
    }

    @Override
    public GenreDTO getById(Long id) {

        return this.repository.findById(id)
                .map(this.genreMapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }

    @Override
    public List<GenreDTO> getList(GetCategoriesFilter filter) {
        return repository.filterGenre(filter.getSearchTerm(), filter.getExcludeIds()).stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
