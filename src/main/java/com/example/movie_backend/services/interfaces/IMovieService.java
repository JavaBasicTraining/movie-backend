package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.CreateRequestFileMovie;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.entity.Movie;
import io.minio.errors.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;


public interface IMovieService{

    MovieDTO getById(Long id);
    MovieDTO createFileMovie(CreateRequestFileMovie fileMovie, Long movieId, Set<Long> episodeId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException ;


    MovieDTO createWithEpisode(MovieEpisodeRequest dto);
    MovieDTO updateWithEpisode(Long movieId, MovieEpisodeRequest request);

    Boolean delete(Long id);

    List<MovieDTOWithoutJoin> query(String name);

    Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable);


    void uploadMovieFile(Long id, MultipartFile file, String type );


    void uploadEpisodeFile(Long id, Long episodeId, MultipartFile poster, MultipartFile video);
}
