package com.example.movie_backend.controller.request;

import com.example.movie_backend.dto.episode.EpisodeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class CreateEpisodeRequest extends EpisodeDTO {
    private transient MultipartFile filePoster;

    private transient MultipartFile fileMovie;

}
