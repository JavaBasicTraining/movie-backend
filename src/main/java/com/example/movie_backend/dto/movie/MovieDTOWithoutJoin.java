package com.example.movie_backend.dto.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.minio.ObjectWriteResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties({"categoryDTOSet", "ids"})
public class MovieDTOWithoutJoin extends MovieDTO {



}
