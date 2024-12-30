package com.example.movie_backend.controller.impl;

import com.example.movie_backend.controller.IMovieController;
import com.example.movie_backend.controller.dto.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.enumerate.FilerMovieType;
import com.example.movie_backend.service.IMovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.movie_backend.constant.CustomHeader.X_TOTAL_COUNT;

@RestController
@AllArgsConstructor
public class MovieController implements IMovieController {

    public final IMovieService service;

    @Override
    public ResponseEntity<List<MovieDTOWithoutJoin>> query(
            QueryMovieRequest request,
            Pageable pageable
    ) {
        Page<MovieDTOWithoutJoin> page;
        if (FilerMovieType.TRENDING.equals(request.getFilter())) {
            page = service.getTrendingMovies(request, pageable);
        } else {
            page = service.query(request, pageable);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT, String.valueOf(page.getTotalElements()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(page.getContent());
    }

    @Override
    public ResponseEntity<MovieDTO> getByPath(String path) {
        return ResponseEntity.ok(service.getByPath(path));
    }
}
