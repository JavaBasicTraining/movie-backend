package com.example.movie_backend.service;

import com.example.movie_backend.dto.ChatMessageDTO;
import com.example.movie_backend.dto.user.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IRoomSocketService {
    ChatMessageDTO handleChatMessage(UUID roomId, ChatMessageDTO chatMessageDTO);

    List<UserDTO> loadParticipants(UUID roomId, UserDTO user);
}
