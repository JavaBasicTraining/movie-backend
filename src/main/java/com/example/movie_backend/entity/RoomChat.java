package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "room_chat")
@Entity
@RequiredArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class RoomChat {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "room_name")
    private  String roomName;

    @OneToMany(mappedBy = "room")
    private Set<User> users  =  new HashSet<>();


    @OneToMany
    @JoinColumn(name = "room")
    private Set<Message> messages  = new HashSet<>();

}
