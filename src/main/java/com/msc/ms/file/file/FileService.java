package com.msc.ms.file.file;

import com.msc.ms.file.common.model.CommonVariables;
import com.msc.ms.file.file.error.NoFileFoundException;
import com.msc.ms.file.file.model.FileEntity;
import com.msc.ms.file.file.model.FileRequest;
import com.msc.ms.file.minio.MinioService;
import com.msc.ms.file.typefile.TypeFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final IFileRespository iFileRespository;
    private final MinioService minioService;
    private final TypeFileService typeFileService;
    private static final String COMMON_BUCKET = "common-files";
    private static final String COMMON_IMAGE_USER_NAME = "COMMON-USER-IMAGE.png";
    private static final Integer COMMON_USER_IMAGE_ID = 2;

    // common-files/COMMON-USER-IMAGE.png
    public String saveDefaultUserImage(Integer idUser) throws NoFileFoundException {
        final var pathOfDefaultImage = this.getDefaultResourcePath(COMMON_USER_IMAGE_ID, COMMON_BUCKET);
if(pathOfDefaultImage==null){
    throw new NoFileFoundException("File no found",COMMON_IMAGE_USER_NAME,COMMON_BUCKET);
}
        final var mTypeFile = typeFileService.findById(COMMON_USER_IMAGE_ID);
        final var fileEntityUserProfileImage = FileEntity.builder()
                .userOwner(idUser)
                .path(pathOfDefaultImage)
                .fileName(COMMON_IMAGE_USER_NAME)
                .idTypeFile(mTypeFile)
                .build();
        fileEntityUserProfileImage.setActive(true);
        fileEntityUserProfileImage.setDateCreate(new Date());
        iFileRespository.save(fileEntityUserProfileImage);
        return fileEntityUserProfileImage.getPath();

    }

    public String saveFile(FileRequest fileRequest) {
        return "";
    }


    private String getDefaultResourcePath(Integer typeFileId, String bucket) {
        final var mTypeFile = typeFileService.findById(typeFileId);
        final var file = iFileRespository.findByIdTypeFileAndBucketAndActive(mTypeFile, bucket, CommonVariables.ACTIVE_STATE);
        return file.getPath();
    }
}
