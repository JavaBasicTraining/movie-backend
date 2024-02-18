package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "evaluation")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star")
    private Integer star;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "movie_id")
    private Integer movieId;
}
