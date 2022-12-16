package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "movie")
@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false, columnDefinition = "binary(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "vi_title")
    private String viTitle;

    @Column(name = "en_title")
    private String enTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "movie_package_id")
    private String moviePackageId;

}
