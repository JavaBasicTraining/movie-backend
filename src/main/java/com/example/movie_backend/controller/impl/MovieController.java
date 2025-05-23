package com.example.movie_backend.controller.impl;

import com.example.movie_backend.controller.IMovieController;
import com.example.movie_backend.controller.dto.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.service.impl.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MovieController implements IMovieController {

    public final MovieService service;

    @Override
    public ResponseEntity<List<MovieDTOWithoutJoin>> query(
            QueryMovieRequest request,
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.query(request, pageable).getContent());
    }

    @Override
    public ResponseEntity<MovieDTO> filterMovie(String path) {
        return ResponseEntity.ok(service.filterMovie(path));
    }
}
