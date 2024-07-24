package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.controller.request.CreateEpisodeRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import io.minio.errors.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequestMapping("/api/v1/episode/")
public interface IEpisodeController {
    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<EpisodeDTO> create(@ModelAttribute @Valid CreateEpisodeRequest episode) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    @PutMapping("update")
        ResponseEntity<EpisodeDTO> update(@ModelAttribute @Valid CreateEpisodeRequest episode, @RequestParam Long id);

    @GetMapping("getById/{id}")
    ResponseEntity<EpisodeDTO> getById(@RequestParam Long id);
    @DeleteMapping("delete{id}")
    boolean delete(@RequestParam Long id);}
