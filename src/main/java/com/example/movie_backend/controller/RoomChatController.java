package com.example.movie_backend.controller;
import com.example.movie_backend.controller.interfaces.IRoomChatController;
import com.example.movie_backend.dto.room_chat.RoomChatDTO;
import com.example.movie_backend.services.interfaces.IRoomChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomChatController implements IRoomChatController {
    public  final IRoomChatService service;

    public RoomChatController(IRoomChatService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<RoomChatDTO> create(RoomChatDTO roomChat) {
        return ResponseEntity.ok(service.create(roomChat));
    }

    @Override
    public ResponseEntity<RoomChatDTO> update(RoomChatDTO roomChat, Long id) {
        return ResponseEntity.ok(service.update(roomChat,id));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @Override
    public ResponseEntity<RoomChatDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
