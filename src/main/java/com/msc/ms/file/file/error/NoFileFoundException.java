package com.msc.ms.file.file.error;

import lombok.Getter;

@Getter
public class NoFileFoundException extends Exception{
    private final String bucket;
    private final String reference;

    public NoFileFoundException(String message,String bucket,String reference){
        super(message);
        this.bucket = bucket;
        this.reference=reference;
    }
}
