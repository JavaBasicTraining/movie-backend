package com.example.movie_backend.dto.movie;

import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CreateRequestFileMovie {
    private transient MultipartFile filePoster;

    private transient MultipartFile fileMovie;

    private transient Set<MultipartFile> filePosterEpisode;

    private transient  Set<MultipartFile> fileMovieEpisode;

}
