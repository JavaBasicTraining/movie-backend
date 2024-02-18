package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "movie_category")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
public class MovieCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "category_id")
    private Integer categoryId;
}
