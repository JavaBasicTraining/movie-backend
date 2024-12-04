package com.example.movie_backend.dto;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.enumerate.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class ChatMessageDTO {
    private String content;

    private ZonedDateTime timestamp;

    private MessageType type;

    @JsonIgnoreProperties(value = {"participants"}, allowSetters = true)
    private RoomDTO room;

    private UserDTO sender;
}
