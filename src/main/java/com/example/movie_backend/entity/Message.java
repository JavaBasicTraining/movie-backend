package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Table(name = "message")
@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)

@RequiredArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "message_text")
    private String messageText;

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private  User user  = new User();

    @ManyToOne
    @JoinColumn(name =  "room_id")
    private  RoomChat room  = new RoomChat();
}
