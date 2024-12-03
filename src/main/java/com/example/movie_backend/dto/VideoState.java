package com.example.movie_backend.dto;

import com.example.movie_backend.enumerate.VideoAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class VideoState implements Serializable {
    private String roomId;
    private String username;
    private VideoAction action; // PLAY, PAUSE, SEEK
    private Double timestamp;
    private LocalDateTime eventTime;
    private Boolean playing;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class VideoTimestamp implements Serializable {
        private Double playedSeconds;
        private Double played;
        private Double loadedSeconds;
    }
}
