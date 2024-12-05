package com.example.movie_backend.dto.room_chat;

import com.example.movie_backend.entity.Message;
import com.example.movie_backend.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class RoomChatDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long Id;

    private String roomName;
    @JsonIgnore
    private Set<User> users;

    @JsonIgnore
    private Set<Message> messages;
}
