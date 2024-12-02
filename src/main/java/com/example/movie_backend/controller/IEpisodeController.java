package com.example.movie_backend.controller;

import com.example.movie_backend.controller.dto.request.CreateEpisodeRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import io.minio.errors.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@RequestMapping("/api/v1/episode/")
public interface IEpisodeController {
    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<EpisodeDTO> create(@ModelAttribute @Valid CreateEpisodeRequest episode) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    @PutMapping("update")
    ResponseEntity<EpisodeDTO> update(@ModelAttribute @Valid CreateEpisodeRequest episode, @RequestParam Long id);

    @GetMapping("getById/{id}")
    ResponseEntity<EpisodeDTO> getById(@PathVariable Long id);

    @DeleteMapping("delete/{id}")
    boolean delete(@PathVariable Long id);

    @GetMapping("{movieId}")
    ResponseEntity<Set<EpisodeDTO>> getListEpisodeByMovieId(@PathVariable Long movieId);

    @GetMapping("movieId/{movieId}/episode/{episodeCount}")
    ResponseEntity<EpisodeDTO> getEpisodeByMovieId(@PathVariable Long movieId, @PathVariable Long episodeCount);

}

