package com.msc.ms.file.file.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {
    @NotNull
    private Integer idUser;
    @NotBlank
    private String fileName;
    @NotNull
    private MultipartFile file;
    @NotNull
    private Integer idType;
    @NotNull
    private String bucket;
}
