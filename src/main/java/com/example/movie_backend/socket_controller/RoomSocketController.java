package com.example.movie_backend.socket_controller;

import com.example.movie_backend.dto.ChatMessageDTO;
import com.example.movie_backend.dto.VideoState;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.service.IRoomSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoomSocketController {

    private final IRoomSocketService roomSocketService;

    @MessageMapping("/room/{roomId}/chat")
    @SendTo("/topic/room/{roomId}/chat")
    public ChatMessageDTO handleChat(@DestinationVariable UUID roomId, ChatMessageDTO message) {
        log.info("Handle chat message in room: {}, message: {}", roomId, message);
        return roomSocketService.handleChatMessage(roomId, message);
    }

    @MessageMapping("/room/{roomId}/video")
    @SendTo("/topic/room/{roomId}/video")
    public VideoState handleVideoState(
            @DestinationVariable String roomId,
            VideoState state) {
        log.info("Handle video state in room: {}, state: {}, action: {}", roomId, state.getPlaying(), state.getAction());
        return state;
    }

    @MessageMapping("/room/{roomId}/participants")
    @SendTo("/topic/room/{roomId}/participants")
    public List<UserDTO> handleParticipants(
            @DestinationVariable UUID roomId,
            UserDTO user) {
        log.info("Handle participants in room: {}", roomId);
        return roomSocketService.loadParticipants(roomId, user);
    }
}
