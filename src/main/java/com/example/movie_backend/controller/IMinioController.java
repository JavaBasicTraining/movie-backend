package com.example.movie_backend.controller;


import com.example.movie_backend.dto.file.FileInfo;
import com.example.movie_backend.dto.file.UploadByFile;
import com.example.movie_backend.dto.file.UploadByLink;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/minio")
public interface IMinioController {

    @GetMapping("list")
    ResponseEntity<List<FileInfo>> getList();

    @PostMapping(value = "upload-by-link")
    ResponseEntity<FileInfo> uploadByLink(@RequestBody @Valid UploadByLink request);

    @PostMapping(value = "upload-by-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileInfo> uploadByFile(@ModelAttribute @Valid UploadByFile request);

    @GetMapping("link")
    ResponseEntity<String> getPreSignedLink(@RequestParam("object") String object);
}
