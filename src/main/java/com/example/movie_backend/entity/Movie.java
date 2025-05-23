package com.example.movie_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "movie")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Accessors(chain = true)
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Unique
    @Column(name = "name")
    private String nameMovie;

    @Unique
    @Column(name = "path", unique = true)
    private String path;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "vi_title")
    private String viTitle;

    @Column(name = "en_title")
    private String enTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "country")
    private String country;

    @Column(name = "year")
    private Long year;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Genre> genres = new HashSet<>();

    @JsonIgnoreProperties(value = "movie", allowSetters = true)
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_evaluation",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "evaluation_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Evaluation> evaluations = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"movie"}, allowSetters = true)
    private Set<Episode> episodes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Category category;

    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes.clear();
        episodes.forEach(episode -> episode.setMovie(this));
        this.episodes.addAll(episodes);
    }
}
