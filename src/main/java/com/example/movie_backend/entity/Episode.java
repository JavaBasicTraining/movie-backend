package com.example.movie_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Table(name = "episode")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Episode {
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
    public String setVideoUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return videoUrl;
    }

    @Column(name = "descriptions")
    private String descriptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
