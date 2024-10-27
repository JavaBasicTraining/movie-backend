package com.example.movie_backend.minio.controller;

import com.example.movie_backend.minio.entity.FileInfo;
import com.example.movie_backend.minio.entity.UploadByFile;
import com.example.movie_backend.minio.entity.UploadByLink;
import com.example.movie_backend.minio.service.IMinioService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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


//    @GetMapping(value = "/video")
//    public Flux<Void> streamVideo(@RequestParam("fileName") String fileName, ServerHttpResponse response) {
//        try {
//            InputStream videoStream = minioClient.getObject(
//                    GetObjectArgs.builder()
//                            .bucket(BUCKET_NAME)
//                            .object(fileName)
//                            .build()
//            );
//
//            response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"");
//
//            return DataBufferUtils
//                    .readInputStream(() -> videoStream, response.bufferFactory(), 1024)
//                    .flatMap(dataBuffer -> response.writeWith(Mono.just(dataBuffer)))
//                    .publishOn(Schedulers.boundedElastic())
//                    .doOnTerminate(() -> {
//                        try {
//                            videoStream.close(); // Close InputStream after streaming
//                        } catch (IOException e) {
//                            throw new RuntimeException("Error closing video stream", e);
//                        }
//                    });
//        } catch (Exception e) {
//            return Flux.error(new RuntimeException("Error streaming video", e));
//        }
//    }

    @GetMapping(value = "/video")
    public Mono<ResponseEntity<Flux<DataBuffer>>> streamVideo(@RequestParam String fileName) {
        try {
            InputStream videoStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(fileName)
                            .build()
            );

            Flux<DataBuffer> dataBufferFlux = DataBufferUtils
                    .readInputStream(() -> videoStream, new DefaultDataBufferFactory(), 1024)
                    .doOnTerminate(() -> {
                        try {
                            videoStream.close();
                        } catch (IOException e) {
                            throw new RuntimeException("Error closing video stream", e);
                        }
                    });

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("video/mp4"));
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"");

            // Add Accept-Ranges header
            headers.set(HttpHeaders.ACCEPT_RANGES, "bytes");

            return Mono.just(ResponseEntity.ok().headers(headers).body(dataBufferFlux));
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Error streaming video", e));
        }
    }
}