package com.example.movie_backend.services;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieMapper;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IMovieService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService  implements IMovieService {



    public  final MovieRepository repository;

    public final MovieMapper mapper;
    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MovieDTO create(MovieDTO dto) {
        Movie comment = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MovieDTO update(MovieDTO dto, Long id) {
        Movie comment = mapper.toEntity(dto,id);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public MovieDTO getById(Long id) {
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
