package com.example.movie_backend.services;

import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.dto.genre.GenreMapper;
import com.example.movie_backend.entity.Genre;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.GenreRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService implements IGenreService {

    public final GenreMapper mapper;
    public final GenreRepository repository;
    public final MovieRepository movieRepository;

    @Override
    @Transactional
    public GenreDTO create(GenreDTO dto) {
        Genre category = mapper.toEntity(dto);
        for (Long id : dto.getMovieIds()) {
            Movie movie = movieRepository.findById(id).orElse(null);
            if (Objects.nonNull(movie)) {
                movie.addCategory(category);
            }
        }
        category = repository.save(category);
        return mapper.toDTO(category);
    }

    @Override
    public GenreDTO update(GenreDTO entity, Long id) {
        Genre category = mapper.toEntity(entity, id);
        return mapper.toDTO(repository.save(category));
    }

    @Override
    public GenreDTO getById(Long id) {

        return this.repository.findById(id)
            .map(this.mapper::toDTO)
            .orElseThrow(
                () -> new BadRequestException("Movie not found")
            );
    }

    @Override
    public List<GenreDTO> getList(GetCategoriesFilter filter) {
        return repository.filterGenre(filter.getSearchTerm(), filter.getExcludeIds()).stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }


    @Override
    public Boolean delete(Long id) {
        return null;
    }


//    public Set<CategoryDTO> filter(String nameMovie) {
//
//        return repository.filterCategory(nameMovie).stream()
//                .map(mapper::toDTO).collect(Collectors.toSet());
//    }
}
