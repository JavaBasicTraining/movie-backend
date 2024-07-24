package com.example.movie_backend.controller;

import com.example.movie_backend.controller.request.CreateMovieRequest;
import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.CreateRequestFileMovie;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IMovieService;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

@PreAuthorize("hasAuthority('admin')")
@RequestMapping("api/v1/admin/movies")
@RequiredArgsConstructor
@RestController
public class MovieManageController {
    private final IMovieService movieService;
    private final MovieRepository repository;

    @GetMapping
    public ResponseEntity<List<MovieDTOWithoutJoin>> query(@ParameterObject Pageable pageable,
                                                           @ParameterObject QueryMovieRequest request) {
        Page<MovieDTOWithoutJoin> movieDTOPage = movieService.query(request, pageable);
        return ResponseEntity.ok(movieDTOPage.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(movieService.getById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieDTO> create(@ModelAttribute @Valid CreateMovieRequest movieDTO) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(
                movieService.create(movieDTO, movieDTO.getFilePoster(), movieDTO.getFileMovie())
        );
    }

    @PostMapping(value = "createFileMovie",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieDTO> createFileMovie(@ModelAttribute @Valid CreateRequestFileMovie fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(
                movieService.createFileMovie(fileMovie)
        );
    }

    @PostMapping(value = "createWithEpisode")
    public ResponseEntity<MovieDTO> createWithEpisode(@RequestBody MovieEpisodeRequest movieEpisodeRequest) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(
                movieService.createWithEpisode(movieEpisodeRequest)
        );
    }

    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieDTO> update(@ModelAttribute @Valid CreateMovieRequest movieDTO, @PathVariable Long id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(movieService.update(movieDTO, id, movieDTO.getFilePoster(), movieDTO.getFileMovie()));
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        ResponseEntity.ok(movieService.delete(id));
        return true;
    }
}
