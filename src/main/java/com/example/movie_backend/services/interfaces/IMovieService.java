package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.controller.request.CreateMovieRequest;
import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.movie.CreateRequestFileMovie;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.entity.Episode;
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
    MovieDTO create(CreateMovieRequest dto, MultipartFile filePoster, MultipartFile fileMovie ) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    MovieDTO update(CreateMovieRequest dto, Long id , MultipartFile filePoster, MultipartFile fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    MovieDTO getById(Long id);
    MovieDTO createFileMovie(CreateRequestFileMovie fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException ;

    MovieDTO createWithEpisode(MovieEpisodeRequest dto);


    Boolean delete(Long id);

    List<MovieDTOWithoutJoin> query(String name);

    Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable);
}
