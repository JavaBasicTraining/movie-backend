package com.example.movie_backend.repository;

import com.example.movie_backend.dto.room_chat.RoomChatDTO;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {
    @Query(
            value = """
                SELECT * FROM movie_website.room_chat r
                WHERE r.room_name = :roomName
            """,
            nativeQuery = true
    )
    Optional<RoomChat> filterRoom(@Param("roomName") String roomName);

}
