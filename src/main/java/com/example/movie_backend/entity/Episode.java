package com.example.movie_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.io.Serializable;

@Table(name = "episode")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Episode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "episode_count")
    private Long episodeCount;

    @Column(name = "poster_url")
    private String posterUrl;

    public String setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return posterUrl;
    }

    @Column(name = "video_url")
    private String videoUrl;

    public String setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return videoUrl;
    }

    @Column(name = "descriptions")
    private String descriptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties({"episodes"})
    private Movie movie;

    @Transient
    private String tempId;
}
