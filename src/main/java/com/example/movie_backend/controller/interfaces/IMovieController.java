package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.QueryMovieResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/movies") // mấy cái khác chỉn theo cái này
public interface IMovieController {

    @GetMapping("query")
    ResponseEntity<QueryMovieResponse> query (@RequestParam(name = "categoryName") String categoryName);

    @PostMapping
    ResponseEntity<MovieDTO> create(@RequestBody MovieDTO movieDTO);

    @PutMapping
    ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movieDTO, @RequestParam Long id);

    @GetMapping("{id}")
    ResponseEntity<MovieDTO> getById(@RequestParam Long id);

    @GetMapping("getList")
    ResponseEntity<List<MovieDTO>> getList();

    @DeleteMapping("{id}")
    boolean delete(@RequestParam Long id);
}
