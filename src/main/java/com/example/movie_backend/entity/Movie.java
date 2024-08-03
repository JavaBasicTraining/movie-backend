package com.example.movie_backend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "movie")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return videoUrl;
    }

    public String setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return trailerUrl;
    }

    @Unique
    @Column(name = "name")
    private String nameMovie;

    @Column(name = "poster_url") // trong database thì gạch chân để phần biệt với lại quy tắc chuẩn của database
    private String posterUrl; // images/hinh.png

    @Column(name = "vi_title")
    private String viTitle;

    @Column(name = "en_title")
    private String enTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "video_url")
    private String videoUrl ;

    @Column(name = "trailer_url")
    private String trailerUrl ;

    @Column(name = "country")
    private String country ;

    @Column(name = "year")
    private Long year ;

//    private Boolean status ;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_comment",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private Set<Comment> comments = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_evaluation",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "evaluation_id")
    )
    private Set<Evaluation> evaluations = new HashSet<>();



    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public void addComment(Comment comment) {
        this.comments.add(comment);
    }


    public void addCategory(Genre genre) {
        this.genres.add(genre);
    }
    public void addEvaluation (Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }

}
