package com.msc.ms.file.file.controller;

import com.msc.ms.file.file.FileService;
import com.msc.ms.file.file.error.NoFileFoundException;
import com.msc.ms.file.file.model.FileRequest;

import com.msc.ms.file.minio.error.MinioOperationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;


    @PostMapping
    public ResponseEntity<String> saveFile(@Valid FileRequest request) {
        final var responsePath = fileService.saveFile(request);
        return ResponseEntity.ok(responsePath);
    }

    @PostMapping("/defaultUserImage")
    public ResponseEntity<String> postMethodName(@RequestParam("userId") Integer userId) throws NoFileFoundException {
        final var pathResponse = this.fileService.saveDefaultUserImage(userId);
        return ResponseEntity.ok(pathResponse);
    }

    @GetMapping("/getFilesNamesFromBucket/{bucketName}")
    public ResponseEntity<List<String>> getFileNamesFromBucket(@PathVariable(name = "bucketName") String pBucketName) throws MinioOperationException {
        return ResponseEntity.ok(fileService.getFileNamesFromBucket(pBucketName));
    }

    @GetMapping("/getFile")
    public ResponseEntity<Resource> getFile(@RequestParam("bucket") String pBucket, @RequestParam("file") String pFile) throws MinioOperationException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + pFile + "\"")
                .body(fileService.getFile(pBucket, pFile));
    }
}
