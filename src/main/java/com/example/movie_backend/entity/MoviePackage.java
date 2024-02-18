package com.example.movie_backend.entity;

import com.example.movie_backend.entity.enumerate.MoviePackageType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "movie_package")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
public class MoviePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MoviePackageType type;
}
