package com.msc.ms.file.minio.error;

public class MinioOperationException extends Exception {
    private final String bucket;

    public MinioOperationException(String message, String bucket) {
        super(message);
        this.bucket = bucket;
    }
}
