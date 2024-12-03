package com.example.movie_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie_backend.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
}
