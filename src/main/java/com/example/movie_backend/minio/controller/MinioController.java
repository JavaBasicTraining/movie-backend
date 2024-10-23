package com.example.movie_backend.minio.controller;

import com.example.movie_backend.minio.entity.FileInfo;
import com.example.movie_backend.minio.entity.UploadByFile;
import com.example.movie_backend.minio.entity.UploadByLink;
import com.example.movie_backend.minio.service.IMinioService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;


@RestController
public class MinioController implements IMinioController {
    public final IMinioService iMinioService;
    private final MinioClient minioClient;
    private static final String BUCKET_NAME = "movie";


    public MinioController(IMinioService iMinioService, MinioClient minioClient) {
        this.iMinioService = iMinioService;
        this.minioClient = minioClient;
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


//    @GetMapping("/stream")
//    public ResponseEntity<InputStreamResource> streamVideo(@RequestHeader HttpHeaders headers) {
//        try {
//            InputStreamResource videoStream = iMinioService.chunkVideo(headers);
//            List<HttpRange> ranges = headers.getRange();
//            if (ranges.isEmpty()) {
//                long fileSize = iMinioService.getFileSize("movie", "myvideo.mp4");
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
//                        .contentLength(fileSize)
//                        .body(videoStream);
//            } else {
//                HttpRange range = ranges.get(0);
//                long fileSize = iMinioService.getFileSize("movie", "myvideo.mp4");
//                long start = range.getRangeStart(0);
//                long end = range.getRangeEnd(fileSize - 1);
//                long contentLength = end - start + 1;
//
//                return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
//                        .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
//                        .header(HttpHeaders.CONTENT_RANGE, "bytes " + start + "-" + end + "/" + fileSize)
//                        .contentLength(contentLength)
//                        .body(videoStream);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


    @SneakyThrows
    @GetMapping(value = "/video")
    public Flux<DataBuffer> streamVideo(@RequestParam String fileName, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        InputStream inputStream;
        try {
            inputStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            return Flux.error(new RuntimeException("Error fetching video from Minio", e));
        }
        return Flux.generate(
                () -> inputStream,
                (input, sink) -> {
                    try {
                        byte[] buffer = new byte[4096];
                        int bytesRead = input.read(buffer);
                        if (bytesRead != -1) {
                            byte[] data = Arrays.copyOf(buffer, bytesRead);
                            DataBuffer dataBuffer = new DefaultDataBufferFactory().wrap(data);
                            sink.next(dataBuffer);
                        } else {
                            sink.complete();
                        }
                    } catch (Exception e) {
                        sink.error(e);
                    }
                    return input;
                },
                input -> {
                    try {
                        input.close();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                }
        );
    }


}
