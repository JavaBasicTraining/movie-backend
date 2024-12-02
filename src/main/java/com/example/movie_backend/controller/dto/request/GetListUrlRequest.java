package com.example.movie_backend.controller.dto.request;

import com.example.movie_backend.dto.movie.MovieDTO;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class GetListUrlRequest extends MovieDTO {

}
