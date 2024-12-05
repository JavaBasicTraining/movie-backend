package com.example.movie_backend.repository;

import com.example.movie_backend.entity.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {

}
