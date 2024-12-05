package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IMessageController;
import com.example.movie_backend.dto.message.MessageDTO;
import com.example.movie_backend.services.interfaces.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MessageController implements IMessageController {
    public  final IMessageService service;

    public MessageController(IMessageService service) {
        this.service = service;
    }

    @GetMapping
    public String chat() {
        return "Chat is ready";
    }
    @Override
    public ResponseEntity<MessageDTO> create(MessageDTO roomChat) {
        return ResponseEntity.ok(service.create(roomChat));
    }

    @Override
    public ResponseEntity<MessageDTO> update(MessageDTO roomChat, Long id) {
        return ResponseEntity.ok(service.update(roomChat,id));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<MessageDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public ResponseEntity<List<MessageDTO>> getListMessage() {
        return ResponseEntity.ok(service.getListMessage());
    }
}
