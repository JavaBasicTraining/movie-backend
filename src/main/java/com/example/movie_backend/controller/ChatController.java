package com.example.movie_backend.controller;

import com.example.movie_backend.controller.request.VideoState;
import com.example.movie_backend.dto.message.MessageDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.services.interfaces.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatController {


    private final SimpMessagingTemplate messagingTemplate;
    public final IMessageService service;

    public ChatController(SimpMessagingTemplate messagingTemplate, IMessageService service) {
        this.messagingTemplate = messagingTemplate;
        this.service = service;
    }

    @GetMapping
    public String chat() {
        return "Chat is ready";
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public MessageDTO sendMessage(MessageDTO message) {
        service.create(message);
        log.info("New message: {}", message);
        return message;
    }


    @MessageMapping("/room/{roomId}/video")
    @SendTo("/topic/room/{roomId}/video")
    public VideoState sendVideo(@DestinationVariable Long roomId,  VideoState videoState) throws Exception {
        log.info("Handle video in room:{}, videoState{}, action:{}",roomId,videoState.getVideoState(), videoState.getAction() );

        return videoState;
    }
}
