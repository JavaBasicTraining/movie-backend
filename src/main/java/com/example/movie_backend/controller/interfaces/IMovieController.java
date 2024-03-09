package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.model.file.UploadRequest;
import com.example.movie_backend.model.file.UploadResponse;
import com.example.movie_backend.model.movie.CreateMovieRequest;
import com.example.movie_backend.model.movie.MovieDTO;
import com.example.movie_backend.model.movie.QueryRequest;
import com.example.movie_backend.model.movie.UpdateMovieRequest;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/movies")
public interface IMovieController {
    
    @GetMapping
    ResponseEntity<List<MovieDTO>> query(@ParameterObject QueryRequest request, @ParameterObject Pageable pageable);
    
    @GetMapping("{id}")
    ResponseEntity<MovieDTO> find(@PathVariable("id") UUID id);
    
    @PostMapping
    ResponseEntity<MovieDTO> create(@RequestBody @Valid CreateMovieRequest request);
    
    @PutMapping("{id}")
    ResponseEntity<MovieDTO> update(@PathVariable("id") UUID id, @RequestBody @Valid UpdateMovieRequest request);

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UploadResponse> upload(@ModelAttribute @Valid UploadRequest request);
}
