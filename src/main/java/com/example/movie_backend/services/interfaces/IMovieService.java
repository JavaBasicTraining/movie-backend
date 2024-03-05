package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.model.movie.CreateMovieRequest;
import com.example.movie_backend.model.movie.MovieDTO;
import com.example.movie_backend.model.movie.QueryRequest;
import com.example.movie_backend.model.movie.UpdateMovieRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMovieService {
    Movie getByTitle(String title);
    
    List<MovieDTO> query(QueryRequest request, Pageable pageable);
    
    MovieDTO find(UUID id);
    
    MovieDTO create(CreateMovieRequest request);
    
    MovieDTO update(UUID id, UpdateMovieRequest request);
}
