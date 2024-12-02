package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMoviePackageController;
import com.example.movie_backend.dto.moviepakage.MoviePackageDTO;
import com.example.movie_backend.service.impl.MoviePackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviePackageController implements IMoviePackageController {

    public final MoviePackageService service;

    public MoviePackageController(MoviePackageService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<MoviePackageDTO> create(MoviePackageDTO moviePackageDTO) {
        return ResponseEntity.ok(service.create(moviePackageDTO));
    }

    @Override
    public ResponseEntity<MoviePackageDTO> update(MoviePackageDTO moviePackageDTO, Long id) {
        return ResponseEntity.ok(service.update(moviePackageDTO, id));

    }

    @Override
    public ResponseEntity<MoviePackageDTO> getById(Long id, String a) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public boolean delete(Long id) {
        ResponseEntity.ok(service.delete(id));
        return true;
    }
}
