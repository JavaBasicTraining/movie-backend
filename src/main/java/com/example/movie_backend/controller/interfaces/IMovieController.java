package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.movie.MovieDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/movie/")
public interface IMovieController {

    @PostMapping
    ResponseEntity<MovieDTO> create(@RequestBody MovieDTO movieDTO);

    @PutMapping
    ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movieDTO, @RequestParam Long id);

    @GetMapping("{id}")
    ResponseEntity<MovieDTO> getById(@RequestParam Long id);
    @DeleteMapping("{id}")
    boolean delete(@RequestParam Long id);
}
