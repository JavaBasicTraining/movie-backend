package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "comment")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "episode_id")
    private String episodeId;

    @Column(name = "user_id")
    private Integer userId;
}
