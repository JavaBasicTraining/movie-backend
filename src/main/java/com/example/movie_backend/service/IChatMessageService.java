package com.example.movie_backend.service;

import com.example.movie_backend.dto.ChatMessageDTO;
import org.springframework.scheduling.annotation.Async;

public interface IChatMessageService {

    @Async
    ChatMessageDTO createAsync(ChatMessageDTO chatMessageDTO);

    ChatMessageDTO create(ChatMessageDTO chatMessageDTO);
}
