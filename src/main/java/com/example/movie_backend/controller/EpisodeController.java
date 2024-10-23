package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IEpisodeController;
import com.example.movie_backend.controller.request.CreateEpisodeRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.services.EpisodeService;
import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@RestController
public class EpisodeController implements IEpisodeController {

    public final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<EpisodeDTO> create(CreateEpisodeRequest episode) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(service.create(episode, episode.getFilePoster(), episode.getFileMovie()));

    }

    @Override
    public ResponseEntity<EpisodeDTO> update(CreateEpisodeRequest episode, Long id) {
        return ResponseEntity.ok(service.update(episode, id));

    }

    @Override
    public ResponseEntity<EpisodeDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @Override
    public boolean delete(Long id) {
        ResponseEntity.ok(service.delete(id));

        return true;
    }

    @Override
    public ResponseEntity<Set<EpisodeDTO>> getListEpisodeByMovieId(Long movieId) {
        return ResponseEntity.ok(service.getListEpisodeByMovieId(movieId));
    }

    @Override
    public ResponseEntity<EpisodeDTO> getEpisodeByMovieId(Long movieId, Long episodeCount) {
        return ResponseEntity.ok(service.getEpisodeByMovieId(movieId, episodeCount));
    }
}
