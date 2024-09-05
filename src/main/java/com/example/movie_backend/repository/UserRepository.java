package com.example.movie_backend.repository;

import com.example.movie_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value = """
            SELECT * 
            FROM app_user_authority """, nativeQuery = true)
    Set<User> getUserAuthority();

    @Query(
            value = """
                    SELECT *
                    from movie_website.user
                    WHERE username LIKE %:name%
                       """,
            nativeQuery = true
    )
    Optional<User> filterUser(@Param("name") String name);


}
