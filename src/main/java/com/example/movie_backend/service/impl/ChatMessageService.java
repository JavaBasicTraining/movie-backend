package com.example.movie_backend.service.impl;

import com.example.movie_backend.dto.ChatMessageDTO;
import com.example.movie_backend.entity.ChatMessage;
import com.example.movie_backend.mapper.ChatMessageMapper;
import com.example.movie_backend.repository.ChatMessageRepository;
import com.example.movie_backend.service.IChatMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public ChatMessageDTO createAsync(ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
        chatMessage = chatMessageRepository.save(chatMessage);
        return chatMessageMapper.toDTO(chatMessage);
    }
}
