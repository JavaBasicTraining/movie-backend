package com.example.movie_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "movie")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "poster_url") // trong database thì gạch chân để phần biệt với lại quy tắc chuẩn của database
    private String posterUrl; // images/hinh.png

    @Column(name = "vi_title")
    private String viTitle;

    @Column(name = "en_title")
    private String enTitle;

    @Column(name = "video_minio_path")
    private String videoMinioPath;

    @Column(name = "description")
    private String description;

    @Column(name = "movie_package_id")
    private String moviePackageId;


    @ManyToMany(mappedBy = "movieSet")
    private Set<Category> categorySet = new HashSet<>();

}
