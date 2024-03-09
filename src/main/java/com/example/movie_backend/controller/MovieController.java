package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMovieController;
import com.example.movie_backend.model.file.UploadRequest;
import com.example.movie_backend.model.file.UploadResponse;
import com.example.movie_backend.model.movie.CreateMovieRequest;
import com.example.movie_backend.model.movie.MovieDTO;
import com.example.movie_backend.model.movie.QueryRequest;
import com.example.movie_backend.model.movie.UpdateMovieRequest;
import com.example.movie_backend.services.interfaces.IMinioService;
import com.example.movie_backend.services.interfaces.IMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MovieController implements IMovieController {
    
    private final IMovieService movieService;

    private final IMinioService minioService;

    @Override
    public ResponseEntity<List<MovieDTO>> query(QueryRequest request, Pageable pageable) {
        return ResponseEntity.ok(
            this.movieService.query(request, pageable)
        );
    }
    
    @Override
    public ResponseEntity<MovieDTO> find(UUID id) {
        return ResponseEntity.ok(
            this.movieService.find(id)
        );
    }
    
    @Override
    public ResponseEntity<MovieDTO> create(CreateMovieRequest request) {
        return ResponseEntity.ok(
            this.movieService.create(request)
        );
    }
    
    @Override
    public ResponseEntity<MovieDTO> update(UUID id, UpdateMovieRequest request) {
        return ResponseEntity.ok(
            this.movieService.update(id, request)
        );
    }

    @Override
    public ResponseEntity<UploadResponse> upload(UploadRequest request) {
        return ResponseEntity.ok(this.minioService.upload(request));
    }
}
