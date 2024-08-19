package com.example.movie_backend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "evaluation")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Evaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "star")
    private Long star;

    @Column(name = "movie_id")
    private Long movieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @ManyToMany(mappedBy = "evaluations")
    private Set<Movie> movies = new HashSet<>();
}
