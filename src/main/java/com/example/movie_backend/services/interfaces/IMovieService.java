package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import io.minio.errors.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IMovieService{
    MovieDTO create(MovieDTO dto, MultipartFile filePoster, MultipartFile fileMovie) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    MovieDTO update(MovieDTO dto, Long id , MultipartFile filePoster, MultipartFile fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    MovieDTO getById(Long id);



    Boolean delete(Long id);

    List<MovieDTOWithoutJoin> query(String name);

    Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable);
}
