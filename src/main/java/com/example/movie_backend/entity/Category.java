package com.example.movie_backend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "category")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "categories")
    private Set<Movie> movies = new HashSet<>();

}
