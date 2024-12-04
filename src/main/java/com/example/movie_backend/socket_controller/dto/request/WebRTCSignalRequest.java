package com.example.movie_backend.socket_controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WebRTCSignalRequest {
    private String type; // "offer", "answer", "ice-candidate"
    private String roomId;
    private String userId;
    private Object payload;
}
