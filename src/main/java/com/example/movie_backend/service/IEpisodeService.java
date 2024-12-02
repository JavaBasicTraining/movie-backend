package com.example.movie_backend.service;

import com.example.movie_backend.controller.dto.request.CreateEpisodeRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

public interface IEpisodeService {
    EpisodeDTO create(CreateEpisodeRequest dto, MultipartFile filePoster, MultipartFile fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    EpisodeDTO update(CreateEpisodeRequest dto, Long id);

    EpisodeDTO getById(Long id);

    Boolean delete(Long id);

    Set<EpisodeDTO> getListEpisodeByMovieId(Long id);

    EpisodeDTO getEpisodeByMovieId(Long movieId, Long episodeCount);
}
