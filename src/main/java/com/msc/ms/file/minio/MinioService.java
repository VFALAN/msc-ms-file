package com.msc.ms.file.minio;

import com.msc.ms.file.file.model.FileRequest;
import com.msc.ms.file.minio.error.MinioOperationException;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;


    public ObjectWriteResponse saveFile(FileRequest fileRequest) throws MinioOperationException {
        try {
            final var uploadObject = PutObjectArgs.builder()
                    .object(fileRequest.getFileName())
                    .bucket(fileRequest.getBucket())
                    .stream(
                            fileRequest.getFile()
                                    .getInputStream(),
                            fileRequest.getFile().getSize(),
                            -1
                    ).build();
            return this.minioClient.putObject(uploadObject);
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            throw new MinioOperationException("error in minio service", fileRequest.getBucket());
        }
    }
}
