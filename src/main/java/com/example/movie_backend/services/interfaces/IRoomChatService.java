package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.room_chat.RoomChatDTO;

import java.util.Optional;

public interface IRoomChatService {
    RoomChatDTO create(RoomChatDTO roomChat);

    RoomChatDTO update(RoomChatDTO roomChat, Long id);

    Boolean delete(Long id);

    RoomChatDTO getById(Long id);

    RoomChatDTO filterRoomByName (String roomName);
}
