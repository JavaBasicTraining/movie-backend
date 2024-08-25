package com.example.movie_backend.services;

import com.example.movie_backend.controller.request.CreateEpisodeRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.episode.EpisodeMapper;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.minio.service.MinioService;
import com.example.movie_backend.repository.EpisodeRepository;
import com.example.movie_backend.services.interfaces.IEpisodeService;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EpisodeService implements IEpisodeService {
    public final EpisodeRepository repository;
    private static final String BUCKET_NAME = "student";
    public final EpisodeMapper mapper;

    private final MinioClient minioClient;
    public final MinioService minioService;


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

    public void uploadByFile(MultipartFile file, String object) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ObjectWriteResponse responsePoster = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(object)
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build()
        );
    }

    public EpisodeService(EpisodeRepository repository, EpisodeMapper mapper, MinioClient minioClient, MinioService minioService) {
        this.repository = repository;
        this.mapper = mapper;
        this.minioClient = minioClient;
        this.minioService = minioService;
    }

    @Override
    public EpisodeDTO create(CreateEpisodeRequest dto, MultipartFile filePoster, MultipartFile fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Episode episode = mapper.toEntity(dto);

        String contentTypePoster = filePoster.getContentType();
        if (isImage(contentTypePoster)) {
            String posterPath = "poster" + "/" + filePoster.getOriginalFilename();
            uploadByFile(dto.getFilePoster(), posterPath);
            episode.setPosterUrl(posterPath);
        } else {
            throw new RuntimeException();
        }
        String contentTypeMovie = fileMovie.getContentType();

        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getOriginalFilename();
            uploadByFile(fileMovie, videoPath);
            episode.setVideoUrl(videoPath);

        } else {
            throw new RuntimeException();
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
        return this.repository.getListEpisodeByMovieId(movieId)
                .stream().map(this.mapper::toDTO)
                .collect(Collectors.toSet());

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
                            String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl(), "movie");
                            episodeDTO.setPosterUrl(linkPoster);
                            String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl(), "movie");
                            episodeDTO.setVideoUrl(linkVideo);
                        }
                        return episodeDTO;
                    })
                    .orElseThrow(
                            () -> new BadRequestException("Movie not found")
                    );

        }

    }
}
