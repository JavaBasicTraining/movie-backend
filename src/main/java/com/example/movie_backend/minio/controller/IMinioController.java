package com.example.movie_backend.minio.controller;


import com.example.movie_backend.minio.entity.FileInfo;
import com.example.movie_backend.minio.entity.UploadByFile;
import com.example.movie_backend.minio.entity.UploadByLink;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Struct;
import java.util.List;

@RequestMapping("/api/v1/minio/")
public interface IMinioController {

    @GetMapping("list")
    ResponseEntity<List<FileInfo>> getList();

    @PostMapping(value = "upload-by-link")
    ResponseEntity<FileInfo> uploadByLink(@RequestBody @Valid UploadByLink request);

    @PostMapping(value = "upload-by-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileInfo> uploadByFile(@ModelAttribute @Valid UploadByFile request);

    @GetMapping("link")
    ResponseEntity<String> getPreSignedLink (@RequestParam("object") String object);
}