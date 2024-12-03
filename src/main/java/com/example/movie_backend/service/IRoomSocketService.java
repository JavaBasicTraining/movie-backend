package com.example.movie_backend.service;

import com.example.movie_backend.dto.ChatMessage;
import com.example.movie_backend.dto.user.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IRoomSocketService {
    ChatMessage handleChatMessage(UUID roomId, ChatMessage chatMessage);

    List<UserDTO> loadParticipants(UUID roomId, UserDTO user);
}
