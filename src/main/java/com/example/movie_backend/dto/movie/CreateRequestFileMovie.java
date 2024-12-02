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
    private MultipartFile filePoster;

    private MultipartFile fileMovie;

    private Set<MultipartFile> filePosterEpisode;

    private Set<MultipartFile> fileMovieEpisode;
}
