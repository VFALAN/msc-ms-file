package com.msc.ms.file.common.model.dto;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRequestDTO {

    private String message;
    private Map<String,String> errors;
}
