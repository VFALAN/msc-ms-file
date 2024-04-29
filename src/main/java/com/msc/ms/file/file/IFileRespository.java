package com.msc.ms.file.file;

import com.msc.ms.file.file.model.FileEntity;
import com.msc.ms.file.typefile.TypeFileEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileRespository extends JpaRepository<FileEntity, Integer> {

    public FileEntity findByIdTypeFileAndBucketAndActive(TypeFileEntity idTypeFile, String bucket, boolean active);
}
