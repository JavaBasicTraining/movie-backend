package com.example.movie_backend.minio.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "File")
@Getter
@Setter
@SuperBuilder (toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FileEntity {

    private String name;

    private String moduleId;

    @Lob
    @Column(length = 1000)
    String url;

   private  byte[] content;
   private String bucketName;

}
