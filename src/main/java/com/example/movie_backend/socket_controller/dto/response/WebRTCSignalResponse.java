package com.example.movie_backend.socket_controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WebRTCSignalResponse {
    private String type;
    private String roomId;
    private String userId;
    private Object payload;
}
