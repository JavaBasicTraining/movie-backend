package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.message.MessageDTO;

import java.util.List;

public interface IMessageService {
    MessageDTO create(MessageDTO roomChat);

    MessageDTO update(MessageDTO roomChat, Long id);

    Boolean delete(Long id);

    MessageDTO getById(Long id);
     List<MessageDTO> getListMessage();
}
