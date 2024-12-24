package com.example.movie_backend.service;

import com.example.movie_backend.controller.dto.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IMovieService {
    MovieDTO getById(Long id);

    MovieDTO createWithEpisode(MovieDTO dto);

    MovieDTO updateWithEpisode(Long movieId, MovieDTO request);

    Boolean delete(Long id);

    List<MovieDTOWithoutJoin> query(String keyword, String genre, String country);

    Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable);

    void uploadMovieFile(Long id, MultipartFile file, String type);

    void uploadEpisodeFile(Long id, Long episodeId, MultipartFile file, String type);
}
