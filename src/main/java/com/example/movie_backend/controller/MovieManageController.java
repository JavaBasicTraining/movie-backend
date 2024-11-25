package com.example.movie_backend.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.movie.CreateRequestFileMovie;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.services.interfaces.IMovieService;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;

@PreAuthorize("hasAuthority('admin')")
@RequestMapping("api/v1/admin/movies")
@RequiredArgsConstructor
@RestController
public class MovieManageController {

    private final IMovieService movieService;

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


    @PostMapping(value = "createFileMovie/movieId/{movieId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieDTO> createFileMovie(@ModelAttribute @Valid CreateRequestFileMovie fileMovie, @PathVariable @Valid Long movieId, @RequestParam Set<Long> episodeId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(
            movieService.createFileMovie(fileMovie, movieId, episodeId)
        );
    }

    @PostMapping()
    public ResponseEntity<MovieDTO> createWithEpisode(@RequestBody MovieEpisodeRequest movieEpisodeRequest) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(
            movieService.createWithEpisode(movieEpisodeRequest)
        );
    }
    @PutMapping(value = "{id}")
    public ResponseEntity<MovieDTO> updateWithEpisode(@PathVariable Long id, @RequestBody MovieEpisodeRequest request) {
        return ResponseEntity.ok(
            movieService.updateWithEpisode(id, request)
        );
    }

    @PatchMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@PathVariable("id") Long id,
                                           @RequestParam("type") String type,
                                           @RequestPart("file")
                                           MultipartFile file) {
        movieService.uploadMovieFile(id, file, type);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "{id}/episodes/{episodeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadEpisodeFile(@PathVariable("id") Long id,
                                                  @PathVariable("episodeId") Long episodeId,
                                                  @RequestPart("poster") MultipartFile poster,
                                                  @RequestPart(value = "video", required = false) MultipartFile video) {
        movieService.uploadEpisodeFile(id, episodeId, poster, video);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Long id) {
        ResponseEntity.ok(movieService.delete(id));
        return true;
    }
}
  
