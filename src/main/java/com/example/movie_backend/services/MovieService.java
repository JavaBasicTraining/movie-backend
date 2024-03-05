package com.example.movie_backend.services;

import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.mapper.MovieMapper;
import com.example.movie_backend.model.movie.CreateMovieRequest;
import com.example.movie_backend.model.movie.MovieDTO;
import com.example.movie_backend.model.movie.QueryRequest;
import com.example.movie_backend.model.movie.UpdateMovieRequest;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    
    @Override
    public Movie getByTitle(String title) {
        Optional<Movie> optional = movieRepository.getByViTitle(title);
        return optional.orElse(null);
    }
    
    @Override
    public List<MovieDTO> query(QueryRequest request, Pageable pageable) {
        return this.movieRepository.query(request.getCategoryName(), pageable)
            .map(movieMapper::toDTO)
            .getContent();
    }
    
    @Override
    public MovieDTO find(UUID id) {
        return this.movieRepository.findById(id)
            .map(movieMapper::toDTO)
            .orElseThrow();
    }
    
    @Override
    public MovieDTO create(CreateMovieRequest request) {
        Movie movie = this.movieRepository.save(
            this.movieMapper.fromCreateRequest(request)
        );
        return this.movieMapper.toDTO(movie);
    }
    
    @Override
    public MovieDTO update(UUID id, UpdateMovieRequest request) {
        Movie movie = this.movieRepository.save(
            this.movieMapper.fromUpdateRequest(id, request)
        );
        return this.movieMapper.toDTO(movie);
    }
}
