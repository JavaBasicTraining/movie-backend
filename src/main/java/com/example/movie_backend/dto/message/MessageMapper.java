package com.example.movie_backend.dto.message;
import com.example.movie_backend.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message toEntity  (MessageDTO dto)
    {
        return Message.builder()
                .id(dto.getId())
                .messageText(dto.getMessageText())
                .user(dto.getUser())
                .room(dto.getRoom())
                .build();
    }
    public Message toEntity  (MessageDTO dto,Long id)
    {
        return Message.builder()
                .id(id)
                .messageText(dto.getMessageText())
                .user(dto.getUser())
                .room(dto.getRoom())
                .build();
    }
    public MessageDTO toDTO  (Message entity)
    {
        return MessageDTO.builder()
                .id(entity.getId())
                .messageText(entity.getMessageText())
                .user(entity.getUser())
                .room(entity.getRoom())
                .build();
    }
}
