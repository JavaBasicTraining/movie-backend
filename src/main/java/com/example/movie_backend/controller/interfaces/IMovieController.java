package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * cái này dùng cho user
 */
@RequestMapping("api/v1/movies")
public interface IMovieController {
    /**
     * query movie by filter
     *
     * @param request  request
     * @param pageable pageable
     * @return ResponseEntity<List < MovieDTOWithoutJoin>>
     */
    @GetMapping
    ResponseEntity<List<MovieDTOWithoutJoin>> query(
        @ParameterObject QueryMovieRequest request,
        @ParameterObject Pageable pageable
    );

    @GetMapping("{id}")
    ResponseEntity<MovieDTO> getById(@RequestParam Long id);


}
