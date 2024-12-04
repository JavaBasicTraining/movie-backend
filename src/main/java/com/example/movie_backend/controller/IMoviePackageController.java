package com.example.movie_backend.controller;

import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/movie-package")
public interface IMoviePackageController {

    @PostMapping
    ResponseEntity<MoviePackageDTO> create(@RequestBody MoviePackageDTO moviePackageDTO);

    @PutMapping
    ResponseEntity<MoviePackageDTO> update(@RequestBody MoviePackageDTO moviePackageDTO, @RequestParam Long id);

    @GetMapping("{id}")
    ResponseEntity<MoviePackageDTO> getById(@PathVariable("id") Long id, @RequestParam String a);

    @DeleteMapping("{id}")
    boolean delete(@RequestParam Long id);
}
