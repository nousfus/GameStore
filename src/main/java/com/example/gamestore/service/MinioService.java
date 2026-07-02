package com.example.gamestore.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    public InputStream getFile(String bucket, String objectName) throws Exception {

        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build());

    }
    public String upload(MultipartFile file, String bucket) throws Exception {

        String fileName = System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename().replaceAll("\\s+", "_");

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .stream(
                                file.getInputStream(),
                                file.getSize(),
                                -1)
                        .contentType(file.getContentType())
                        .build());

        return fileName;
    }

    public void delete(String bucket, String objectName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build());
    }

}
