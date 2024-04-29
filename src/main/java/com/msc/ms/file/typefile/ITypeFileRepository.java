package com.msc.ms.file.typefile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeFileRepository extends JpaRepository<TypeFileEntity, Integer> {
     TypeFileEntity findByName(String name);
}
