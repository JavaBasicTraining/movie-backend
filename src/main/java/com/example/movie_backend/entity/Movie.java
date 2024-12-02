package com.example.movie_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "movie")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Unique
    @Column(name = "name")
    private String nameMovie;

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

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<LikeComment> likeComments = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_evaluation",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "evaluation_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Evaluation> evaluations = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"movie"}, allowSetters = true)
    private List<Episode> episodes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Category category;

    public void addCategory(Genre genre) {
        this.genres.add(genre);
    }

    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(episode -> episode.setMovie(this));
        this.episodes = episodes;
    }
}
