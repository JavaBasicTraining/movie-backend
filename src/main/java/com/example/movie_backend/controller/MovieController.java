package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMovieController;
import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<MovieDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("name/{nameMovie}")
    public ResponseEntity<MovieDTO> filterMovie(@PathVariable String nameMovie) {

        return ResponseEntity.ok(service.filterMovie(nameMovie));
    }

}