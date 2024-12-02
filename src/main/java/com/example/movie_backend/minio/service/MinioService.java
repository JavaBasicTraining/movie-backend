package com.example.movie_backend.minio.service;

import com.example.movie_backend.controller.exception.ServerErrorException;
import com.example.movie_backend.minio.config.MinioProperties;
import com.example.movie_backend.minio.entity.FileInfo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class MinioService implements IMinioService {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public FileInfo uploadByFile(MultipartFile file, String objectName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        ObjectWriteResponse response = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(objectName)
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build()
        );
        return FileInfo.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .url(getUrlFile(response.object()))
                .build();
    }

    @Override
    public FileInfo uploadByLink(String link, String filePath) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String fileName = StringUtils.substringAfterLast(link, "/");
        Path path = new File(fileName).toPath();
        String mimeType = Files.probeContentType(path);
        InputStream fileInputStream;
        int fileSize;
        try {
            URLConnection urlConnection = new URL(link).openConnection();
            fileSize = urlConnection.getContentLength();
            fileInputStream = new URL(link).openStream();
        } catch (IOException e) {
            log.error("Error when get url connection", e);
            throw new ServerErrorException(e.getMessage());
        }

        ObjectWriteResponse response = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProperties.getBucket())
                        .object(filePath + "/" + fileName)
                        .contentType(mimeType)
                        .stream(fileInputStream, fileSize, -1)
                        .build()
        );
        return FileInfo.builder()
                .name(fileName)
                .size((long) fileSize)
                .url(getUrlFile(response.object()))
                .build();
    }

    @Override
    public List<FileInfo> getList() {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(minioProperties.getBucket()).build());
        List<FileInfo> fileInfos = new ArrayList<>();
        results.forEach(
                value -> {
                    try {
                        Item item = value.get();

                        fileInfos.add(
                                FileInfo.builder()
                                        .name(item.objectName())
                                        .size(item.size())
                                        .url(
                                                getUrlFile(item.objectName())
                                        )
                                        .build()
                        );

                    } catch (ErrorResponseException | InsufficientDataException | InternalException |
                             InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException |
                             ServerException | XmlParserException e) {
                        log.error("Error when get list files form minio: {}", e.getMessage());
                        throw new ServerErrorException(e.getMessage());
                    }
                }
        );

        return fileInfos;
    }

    @Override
    public String getPreSignedLink(String object) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minioProperties.getBucket())
                            .object(object)
                            .expiry(2, TimeUnit.HOURS)
                            .build());
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.error("Error when get pre-signed url: {}", e.getMessage());
        }
        return null;
    }

    private String getUrlFile(String object) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .object(object)
                        .bucket(minioProperties.getBucket())
                        .method(
                                Method.GET
                        )
                        .build()
        );
    }
}
