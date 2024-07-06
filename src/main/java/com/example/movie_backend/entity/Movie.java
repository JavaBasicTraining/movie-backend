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

    @Column(name = "movie_package_id")
    private String moviePackageId;

    @Column(name = "video_url")
    private String videoUrl ;


    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        this.categories.add(category);
    }

}
