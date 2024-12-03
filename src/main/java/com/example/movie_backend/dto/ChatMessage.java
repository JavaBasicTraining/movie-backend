package com.example.movie_backend.dto;

import com.example.movie_backend.enumerate.MessageType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChatMessage {
    private String roomId;
    private String username;
    private String content;
    private LocalDateTime timestamp;
    private MessageType type;
}
