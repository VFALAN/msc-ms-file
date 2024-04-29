package com.msc.ms.file.file.model;

import com.msc.ms.file.common.model.entity.AuditEntity;
import com.msc.ms.file.typefile.TypeFileEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "TFILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FILE", unique = true)
    @Basic(optional = false)
    private Integer idFile;
    @Column(name = "FILE_NAME")
    @NotBlank
    private String fileName;
    @Column(name = "PATH")
    @NotBlank
    private String path;
    @Column(name = "USER_OWNER")
    @NotNull
    private Integer userOwner;
    @Column(name = "BUCKET")
    private String bucket;
    @JoinColumn(name = "ID_TYPE_FILE")
    @ManyToOne
    private TypeFileEntity idTypeFile;
}
