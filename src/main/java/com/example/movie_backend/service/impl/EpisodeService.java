package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.controller.dto.request.CreateEpisodeRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.mapper.old.EpisodeMapper;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.config.minio.MinioProperties;
import com.example.movie_backend.repository.EpisodeRepository;
import com.example.movie_backend.service.IEpisodeService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.movie_backend.constant.SpecialCharactor.SLASH;

@Service
@RequiredArgsConstructor
public class EpisodeService implements IEpisodeService {
    private final EpisodeRepository repository;
    private final EpisodeMapper mapper;
    private final MinioClient minioClient;
    private final MinioService minioService;
    private final MinioProperties minioProperties;

    @Override
    public EpisodeDTO create(CreateEpisodeRequest dto, MultipartFile filePoster, MultipartFile fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Episode episode = mapper.toEntity(dto);

        String contentTypePoster = filePoster.getContentType();
        if (Objects.nonNull(contentTypePoster) && isImage(contentTypePoster)) {
            String posterPath = "poster" + SLASH + filePoster.getOriginalFilename();
            uploadByFile(dto.getFilePoster(), posterPath);
            episode.setPosterUrl(posterPath);
        } else {
            throw new IllegalArgumentException("Content type not supported");
        }
        String contentTypeMovie = fileMovie.getContentType();

        if (Objects.nonNull(contentTypeMovie) && isVideo(contentTypeMovie)) {
            String videoPath = "video" + SLASH + fileMovie.getOriginalFilename();
            uploadByFile(fileMovie, videoPath);
            episode.setVideoUrl(videoPath);
        } else {
            throw new IllegalArgumentException("Content type not supported");
        }
        return mapper.toDTO(repository.save(episode));

    }

    @Override
    public EpisodeDTO update(CreateEpisodeRequest dto, Long id) {
        Episode comment = mapper.toEntity(dto, id);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public EpisodeDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Episode not found")
                );
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Set<EpisodeDTO> getListEpisodeByMovieId(Long movieId) {
        Set<EpisodeDTO> episodeDTOS = repository.getListEpisodeByMovieId(movieId)
                .stream()
                .map(this.mapper::toDTO)
                .collect(Collectors.toSet());
        return episodeDTOS.stream()
                .sorted(Comparator.comparing(EpisodeDTO::getEpisodeCount))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    @Override
    public EpisodeDTO getEpisodeByMovieId(Long movieId, Long episodeCount) {
        if (episodeCount == null || episodeCount == 0) {
            return null;
        } else {
            return repository.getEpisodeByMovieId(movieId, episodeCount)
                    .map(item -> {
                        EpisodeDTO episodeDTO = mapper.toDTO(item);
                        if (item.getPosterUrl() != null && item.getVideoUrl() != null) {
                            String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                            episodeDTO.setPosterUrl(linkPoster);
                            String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                            episodeDTO.setVideoUrl(linkVideo);
                        }
                        return episodeDTO;
                    })
                    .orElseThrow(
                            () -> new BadRequestException("Movie not found")
                    );
        }
    }

    public void uploadByFile(MultipartFile file, String object) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(object)
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build()
        );
    }

    private boolean isImage(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }

    private boolean isVideo(String contentType) {
        return contentType.equals("video/mp4")
                || contentType.equals("video/quicktime")
                || contentType.equals("video/vnd.dlna.mpeg-tts");
    }
}
