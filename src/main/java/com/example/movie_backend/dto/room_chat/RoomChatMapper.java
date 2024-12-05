package com.example.movie_backend.dto.room_chat;

import com.example.movie_backend.entity.RoomChat;
import org.springframework.stereotype.Component;

@Component
public class RoomChatMapper {
    public RoomChat toEntity  (com.example.movie_backend.dto.room_chat.RoomChatDTO
                                       dto)
    {
        return RoomChat.builder()
                .Id(dto.getId())
                .roomName(dto.getRoomName())
                .users(dto.getUsers())
                .messages(dto.getMessages())
                .build();
    }
    public RoomChat toEntity  (RoomChatDTO dto,Long id)
    {
        return RoomChat.builder()
                .Id(id)
                .roomName(dto.getRoomName())
                .users(dto.getUsers())
                .messages(dto.getMessages())
                .build();
    }
    public RoomChatDTO toDTO  (RoomChat entity)
    {
        return RoomChatDTO.builder()
                .Id(entity.getId())
                .roomName(entity.getRoomName())
                .users(entity.getUsers())
                .messages(entity.getMessages())
                .build();
    }
}
