package com.msc.ms.file.minio;

import com.msc.ms.file.file.model.FileRequest;
import com.msc.ms.file.minio.error.MinioOperationException;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;


    public ObjectWriteResponse saveFile(FileRequest fileRequest) throws MinioOperationException {
        try {
            final var uploadObject = PutObjectArgs.builder().object(fileRequest.getFileName()).bucket(fileRequest.getBucket()).stream(fileRequest.getFile().getInputStream(), fileRequest.getFile().getSize(), -1).build();
            return this.minioClient.putObject(uploadObject);
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.info(e.getMessage());
            throw new MinioOperationException("error in minio service", fileRequest.getBucket());
        }
    }

    public List<String> getFilesNameFromBucket(String pBucketName) throws MinioOperationException {
        final var listObject = new ArrayList<String>();
        try {
            if (bucketExist(pBucketName)) {
                final var response = this.minioClient.listObjects(ListObjectsArgs.builder().bucket(pBucketName).build());
                for (final var item : response) {
                    listObject.add(item.get().objectName());
                }
            }
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            throw new MinioOperationException(e.getMessage(), pBucketName);
        }
        return listObject;
    }


    public boolean bucketExist(String pBucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(pBucketName).build());
    }

    public Resource getFile(String bucket, String file) throws MinioOperationException {

        try {
            final var bytes = this.minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(file).build()).readAllBytes();
            final var mInputStream = new ByteArrayInputStream(bytes);
            return new InputStreamResource(mInputStream);
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
                 InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new MinioOperationException(e.getMessage(), bucket);
        }
    }
}

