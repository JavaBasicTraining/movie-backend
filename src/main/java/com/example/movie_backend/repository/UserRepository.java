package com.example.movie_backend.repository;

import com.example.movie_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByUsername(String username);

    @Modifying
    @Query(value = """
          SELECT * 
          FROM app_user_authority """, nativeQuery = true)
    Set<User> getUserAuthority();

}