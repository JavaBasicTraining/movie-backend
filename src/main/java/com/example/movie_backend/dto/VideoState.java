package com.example.movie_backend.dto;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.enumerate.VideoAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class VideoState implements Serializable {
    @JsonIgnoreProperties(value = {"participants", "host"}, allowSetters = true)
    private RoomDTO room;

    private UserDTO user;

    private VideoAction action;

    private Double timestamp;

    private Instant eventTime;

    private Boolean playing;

    @JsonIgnoreProperties(value = {"episodes", "category", "genres", "evaluations", "comments"}, allowSetters = true)
    private MovieDTO movie;

    private Progress progress;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Progress implements Serializable {
        private Double playedSeconds;
        private Double played;
        private Double loadedSeconds;
    }
}
