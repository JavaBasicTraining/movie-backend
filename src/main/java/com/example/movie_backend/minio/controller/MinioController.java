package com.example.movie_backend.minio.controller;

import com.example.movie_backend.minio.entity.FileInfo;
import com.example.movie_backend.minio.entity.UploadByFile;
import com.example.movie_backend.minio.entity.UploadByLink;
import com.example.movie_backend.minio.service.IMinioService;
import io.minio.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class MinioController implements IMinioController {
    public final IMinioService iMinioService;

    public MinioController(IMinioService iMinioService) {
        this.iMinioService = iMinioService;
    }


    @Override
    public ResponseEntity<List<FileInfo>> getList() {
        return ResponseEntity.ok(iMinioService.getList());
    }

    @Override
    public ResponseEntity<FileInfo> uploadByLink(UploadByLink request) {
        try {
            return ResponseEntity.ok(
                    iMinioService.uploadByLink(
                            request.getLink(),
                            request.getType().getPath()
                    )
            );
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<FileInfo> uploadByFile(UploadByFile request) {
        try {
            return ResponseEntity.ok(
                    iMinioService.uploadByFile(
                            request.getFile(),
                            request.getType().getPath()
                    )
            );
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> getPreSignedLink(String object) {
        return ResponseEntity.ok(iMinioService.getPreSignedLink(object));
    }

// http://localhost:8081/api/v1/minio/list/student/videos/congdianguc.mp4
    // cái param vô tình tạo ra link khác vì có dấu /
    // Như trường hợp này mình đổi nó thành dấu \ này nó nhận được đường link ko a -> k
}