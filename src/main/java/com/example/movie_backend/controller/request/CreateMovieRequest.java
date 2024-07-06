package com.example.movie_backend.controller.request;

import com.example.movie_backend.dto.movie.MovieDTO;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateMovieRequest extends MovieDTO {
    @NotNull
    private transient MultipartFile filePoster;

    @NotNull
    private transient MultipartFile fileMovie;


}
