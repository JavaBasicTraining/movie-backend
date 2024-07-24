package com.example.movie_backend.services;

import com.example.movie_backend.controller.exception.ErrorHandler;
import com.example.movie_backend.controller.request.CreateMovieRequest;
import com.example.movie_backend.controller.request.QueryMovieRequest;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.episode.EpisodeMapper;
import com.example.movie_backend.dto.movie.*;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.minio.service.MinioService;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IMovieService;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {
    private final MinioClient minioClient;
    private static final String BUCKET_NAME = "student";
    public final MovieRepository repository;
    public final MovieMapper mapper;
    public final EpisodeMapper episodeMapper;
    public final MinioService minioService;
    public final ErrorHandler errorHandler;


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

    @Override
    public MovieDTO createFileMovie(CreateRequestFileMovie fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Movie movie = new Movie();

        String contentTypePoster = fileMovie.getFilePoster().getContentType();
        if (isImage(contentTypePoster)) {
            String posterPathMovie = "poster" + "/" + fileMovie.getFilePoster().getOriginalFilename();
            uploadByFile(fileMovie.getFilePoster(), posterPathMovie);
            movie.setPosterUrl(posterPathMovie);
        } else {
            throw new RuntimeException();
        }

        String contentTypeMovie = fileMovie.getFileMovieEpisode().getContentType();

        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getFileMovie().getOriginalFilename();
            uploadByFile(fileMovie.getFileMovie(), videoPath);
            movie.setVideoUrl(videoPath);

        } else {
            throw new RuntimeException();
        }


        String contentPosterEpisode = fileMovie.getFilePoster().getContentType();
        if (isImage(contentPosterEpisode)) {
            String posterPathMovieEpisode = "poster" + "/" + fileMovie.getFilePosterEpisode().getOriginalFilename();
            uploadByFile(fileMovie.getFilePosterEpisode(), posterPathMovieEpisode);
            movie.getEpisodes().stream().map(item -> Episode.builder()
                    .posterUrl(item.setPosterUrl(posterPathMovieEpisode))
                    .build()).collect(Collectors.toSet());

        } else {
            throw new RuntimeException();
        }

        String contentTypeMovieEpisode = fileMovie.getFileMovieEpisode().getContentType();

        if (isVideo(contentTypeMovieEpisode)) {
            String videoPathEpisode = "video" + "/" + fileMovie.getFileMovieEpisode().getOriginalFilename();
            uploadByFile(fileMovie.getFileMovieEpisode(), videoPathEpisode);
            movie.getEpisodes().stream().map(item -> Episode.builder()
                    .videoUrl(item.setVideoUrl(videoPathEpisode))
                    .build()).collect(Collectors.toSet());

        } else {
            throw new RuntimeException();
        }


        return mapper.toDTO(repository.save(movie));
    }


    @Override
    public MovieDTO create(CreateMovieRequest dto, MultipartFile filePoster, MultipartFile fileMovie) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Movie movie = mapper.toEntityCreateMovieRequest(dto);
        String contentTypePoster = filePoster.getContentType();
        if (isImage(contentTypePoster)) {
            String posterPath = "poster" + "/" + filePoster.getOriginalFilename();
            uploadByFile(dto.getFilePoster(), posterPath);
            movie.setPosterUrl(posterPath);
        } else {
            throw new RuntimeException();
        }
        String contentTypeMovie = fileMovie.getContentType();

        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getOriginalFilename();
            uploadByFile(fileMovie, videoPath);
            movie.setVideoUrl(videoPath);

        } else {
            throw new RuntimeException();
        }

        if (Objects.nonNull(dto.getIdGenre()) && !dto.getIdGenre().isEmpty() && dto.getIdGenre().stream().noneMatch(id -> id == 0)) {
            for (EpisodeDTO episode : dto.getEpisodes()) {
                episode.setMovieDTO(mapper.toDTO(movie));
            }
            movie.setEpisodes(dto.getEpisodes().stream().map(item -> episodeMapper.toEntity(item)).collect(Collectors.toSet()));
            return mapper.toDTO(repository.save(movie));
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Ids are null or empty or Ids = 0!!!");
        }
    }

    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        ResponseEntity<Object> response = errorHandler.handleBadRequest(ex);
        return response;
    }

    @Override
    public MovieDTO update(CreateMovieRequest dto, Long id, MultipartFile filePoster, MultipartFile fileMovie) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Movie movie = mapper.toEntityCreateMovieRequest(dto, id);
        String contentTypePoster = filePoster.getContentType();
        if (isImage(contentTypePoster)) {

            String posterPath = "poster" + "/" + filePoster.getOriginalFilename();
            uploadByFile(filePoster, posterPath);
            movie.setPosterUrl(posterPath);
        } else {
            throw new RuntimeException();
        }
        String contentTypeMovie = fileMovie.getContentType();

        if (isVideo(contentTypeMovie)) {
            String videoPath = "video" + "/" + fileMovie.getOriginalFilename();
            uploadByFile(fileMovie, videoPath);
            movie.setVideoUrl(videoPath);

        } else {
            throw new RuntimeException();
        }

        if (Objects.nonNull(dto.getIdGenre()) && !dto.getIdGenre().isEmpty() && dto.getIdGenre().stream().noneMatch(ids -> ids == 0)) {
            return mapper.toDTO(repository.save(movie));
        } else {
            HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad Request");
            throw new HttpClientErrorException(handleHttpClientErrorException(ex).getStatusCode(), "ids are null or empty or ids = 0!!!");

        }

    }

    @Override
    public MovieDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }


    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<MovieDTOWithoutJoin> query(String name) {
        return repository.query(name, Pageable.ofSize(20)).stream().map(
                mapper::toDTOWithoutJoin
        ).toList();
    }

    @Override
    public Page<MovieDTOWithoutJoin> query(QueryMovieRequest request, Pageable pageable) {
        return repository.query(request.getKeyword(), pageable)
                .map(item -> {
                            MovieDTOWithoutJoin movieDTO = mapper.toDTOWithoutJoin(item);
                            if (item.getPosterUrl() != null && item.getVideoUrl() != null) {
                                String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                                movieDTO.setPosterUrl(linkPoster);
                                String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                                movieDTO.setVideoUrl(linkVideo);
                            }
                            return movieDTO;
                        }
                );
    }


    public Set<MovieDTO> filterListMovie(String nameMovie) {

        return repository.filterListMovie(nameMovie).stream()
                .map((mapper::toDTO)).collect(Collectors.toSet());
    }

    public MovieDTO filterMovie(String nameMovie) {
        return repository.filterMovie(nameMovie)
                .map(item -> {
                    MovieDTO movieDTO = mapper.toDTO(item);
                    if (item.getPosterUrl() != null && item.getVideoUrl() != null) {
                        String linkPoster = this.minioService.getPreSignedLink(item.getPosterUrl());
                        movieDTO.setPosterUrl(linkPoster);
                        String linkVideo = this.minioService.getPreSignedLink(item.getVideoUrl());
                        movieDTO.setVideoUrl(linkVideo);
                    }
                    return movieDTO;
                })
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }

    public MovieDTO createWithEpisode(MovieEpisodeRequest dto) {
        Movie movie = mapper.toEntityMovieEpisode(dto);
        if (Objects.nonNull(dto.getIdGenre()) && !dto.getIdGenre().isEmpty() && dto.getIdGenre().stream().noneMatch(id -> id == 0)) {
            for (EpisodeDTO episode : dto.getEpisodesDTO()) {
                episode.setMovieDTO(mapper.toDTO(movie));
            }
            movie.setEpisodes(dto.getEpisodesDTO().stream().map(item -> episodeMapper.toEntity(item)).collect(Collectors.toSet()));
            return mapper.toDTO(repository.save(movie));
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Ids are null or empty or Ids = 0!!!");
        }
    }


}
