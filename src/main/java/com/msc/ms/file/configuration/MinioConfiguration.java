package com.msc.ms.file.configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Configuration
@Slf4j
public class MinioConfiguration {
    @Value("${msc.minio.url}")
    private String URL;
    @Value("${msc.minio.access.secret}")
    private String SECRET;
    @Value("${msc.minio.access.key}")
    private String KEY;
    @Value("#{'${msc.minio.bucket.list}'.split(',')}")
    private List<String> BUCKETS;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(this.URL)
                .credentials(this.KEY, this.SECRET)
                .build();
    }

    @PostConstruct
    public void postConstruct() {
        final var client = this.minioClient();
        this.BUCKETS.forEach(b -> {
            try {
                final var existBucket = client.bucketExists(BucketExistsArgs.builder().bucket(b).build());
                if (existBucket) {
                    log.info("The bucket {} already exists", b);
                } else {
                    log.info("Creating the bucket: {}", b);
                    minioClient().makeBucket(MakeBucketArgs.builder().bucket(b).build());
                    log.info(" the bucket: {} has been created", b);
                }
            } catch (ErrorResponseException | InvalidKeyException | InsufficientDataException | InternalException |
                     InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                     XmlParserException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }
}
