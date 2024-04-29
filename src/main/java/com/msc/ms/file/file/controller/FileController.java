package com.msc.ms.file.file.controller;

import com.msc.ms.file.file.FileService;
import com.msc.ms.file.file.error.NoFileFoundException;
import com.msc.ms.file.file.model.FileRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


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


}
