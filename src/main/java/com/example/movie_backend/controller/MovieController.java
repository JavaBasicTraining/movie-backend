package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMovieController;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.QueryMovieResponse;
import com.example.movie_backend.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController implements IMovieController {

    public final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<QueryMovieResponse> query(String name)
    {
        return ResponseEntity.ok(
                QueryMovieResponse.builder()
                        .movies(
                                service.query(name)
                        )
                        .build()
        );
    }

    @Override
    public ResponseEntity<MovieDTO> create(MovieDTO movieDTO) {
        return ResponseEntity.ok(service.create(movieDTO));
    }

    @Override
    public ResponseEntity<MovieDTO> update(MovieDTO movieDTO, Long id) {
        return ResponseEntity.ok(service.create(movieDTO));
    }

    @Override
    public ResponseEntity<MovieDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<List<MovieDTO>> getList() {
        return ResponseEntity.ok(service.getList());
    }

    @Override
    public boolean delete(Long id) {
         ResponseEntity.ok(service.delete(id));
        return true;
    }
}
