package com.example.movie_backend.socket_controller;

import com.example.movie_backend.mapper.WebRTCSignalMapper;
import com.example.movie_backend.socket_controller.dto.request.WebRTCSignalRequest;
import com.example.movie_backend.socket_controller.dto.response.WebRTCSignalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebRTCSignalingController {

    private final WebRTCSignalMapper webRTCSignalMapper;

    @MessageMapping("/room/{roomId}/webrtc-offer")
    @SendTo("topic/room/{roomId}/webrtc-offer")
    public WebRTCSignalResponse handleOffer(@DestinationVariable String roomId, WebRTCSignalRequest request) {
        log.info("Received offer for room: {}, payload: {}", roomId, request);
        return webRTCSignalMapper.toResponse(request);
    }

    @MessageMapping("/room/{roomId}/webrtc-answer")
    @SendTo("topic/room/{roomId}/webrtc-answer")
    public WebRTCSignalResponse handleAnswer(@DestinationVariable String roomId, WebRTCSignalRequest request) {
        log.info("Received answer for room: {}, payload: {}", roomId, request);
        return webRTCSignalMapper.toResponse(request);
    }

    @MessageMapping("/room/{roomId}/ice-candidate")
    @SendTo("topic/room/{roomId}/ice-candidate")
    public WebRTCSignalResponse handleIceCandidate(@DestinationVariable String roomId, WebRTCSignalRequest request) {
        log.info("Received ICE candidate for room: {}, payload: {}", roomId, request);
        return webRTCSignalMapper.toResponse(request);
    }
}
