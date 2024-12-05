package com.example.movie_backend.dto.message;


import com.example.movie_backend.entity.RoomChat;
import com.example.movie_backend.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
public class MessageDTO {
    public MessageDTO(String messageText) {
        this.messageText = messageText;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String messageText;

    @JsonIgnore
    private User user;

    @JsonIgnore
    private RoomChat room;

}
