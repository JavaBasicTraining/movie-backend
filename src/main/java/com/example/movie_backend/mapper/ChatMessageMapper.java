package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.ChatMessageDTO;
import com.example.movie_backend.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    @Mapping(target = "room.host.authorities", source = "room.host.authorities", ignore = true)
    @Mapping(target = "sender.authorities", source = "sender.authorities", ignore = true)
    ChatMessageDTO toDTO(ChatMessage chatMessage);

    @Mapping(target = "room.host.authorities", source = "room.host.authorities", ignore = true)
    @Mapping(target = "sender.authorities", source = "sender.authorities", ignore = true)
    ChatMessage toEntity(ChatMessageDTO chatMessage);
}
