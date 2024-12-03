package com.example.movie_backend.controller.socket;

import com.example.movie_backend.dto.ChatMessage;
import com.example.movie_backend.dto.VideoState;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.service.IRoomSocketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RoomSocketController {
    private static final Logger log = LoggerFactory.getLogger(RoomSocketController.class);

    private final IRoomSocketService roomSocketService;

    @MessageMapping("/room/{roomId}/chat")
    @SendTo("/topic/room/{roomId}/chat")
    public ChatMessage handleChat(@DestinationVariable UUID roomId, ChatMessage message) {
        log.info("Handle chat message in room: {}, message: {}", roomId, message);
        return roomSocketService.handleChatMessage(roomId, message);
    }

    @MessageMapping("/room/{roomId}/video")
    @SendTo("/topic/room/{roomId}/video")
    public VideoState handleVideoState(
            @DestinationVariable String roomId,
            VideoState state) {
        log.info("Handle video state in room: {}", roomId);
        state.setEventTime(LocalDateTime.now());
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
