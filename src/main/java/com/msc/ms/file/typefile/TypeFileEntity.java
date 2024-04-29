package com.msc.ms.file.typefile;

import com.msc.ms.file.common.model.entity.AuditEntity;
import com.msc.ms.file.file.model.FileEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CTYPE_FILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeFileEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TYPE_FILE", unique = true)
    private Integer idTypeFile;
    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "idTypeFile")
    private List<FileEntity> files;
}
