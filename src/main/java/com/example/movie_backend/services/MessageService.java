package com.example.movie_backend.services;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.message.MessageDTO;
import com.example.movie_backend.dto.message.MessageMapper;
import com.example.movie_backend.entity.Message;
import com.example.movie_backend.repository.MessageRepository;
import com.example.movie_backend.services.interfaces.IMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService implements IMessageService {

    public final MessageRepository repository;

    public final MessageMapper mapper;

    public MessageService(MessageRepository repository, MessageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MessageDTO create(MessageDTO dto) {
        Message saveMessage = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saveMessage);
    }

    @Override
    public MessageDTO update(MessageDTO friend, Long id) {
        if (getById(id) == null) {
            return null;
        }
        Message saveMessage = repository.save(mapper.toEntity(friend));
        return mapper.toDTO(saveMessage);
    }

    @Override
    public Boolean delete(Long id) {
        if (getById(id) == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<MessageDTO> getListMessage() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public MessageDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }
}
