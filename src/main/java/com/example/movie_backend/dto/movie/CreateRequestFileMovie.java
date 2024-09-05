package com.example.movie_backend.dto.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CreateRequestFileMovie {
    private transient MultipartFile filePoster;

    private transient MultipartFile fileMovie;

    private transient Set<MultipartFile> filePosterEpisode;

    private transient Set<MultipartFile> fileMovieEpisode;

}
