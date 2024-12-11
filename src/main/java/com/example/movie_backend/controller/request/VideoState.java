package com.example.movie_backend.controller.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class VideoState {
    private String videoState;
    private String action;
}
