package com.msc.ms.file.typefile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TypeFileService {
    private final ITypeFileRepository iTypeFileRepository;


    public TypeFileEntity findById(Integer pId) {
        final var mOptionalFileEntity = iTypeFileRepository.findById(pId);
        if (mOptionalFileEntity.isPresent()) {
            return mOptionalFileEntity.get();
        } else {
            throw new NoSuchElementException("The Type File with the given id doesn't exist id: " + pId);
        }
    }

    public TypeFileEntity findByName(String name){
        return iTypeFileRepository.findByName(name);
    }
}
