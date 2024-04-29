package com.msc.ms.file.file.controller;

import com.msc.ms.file.common.model.dto.ErrorRequestDTO;
import com.msc.ms.file.file.error.NoFileFoundException;
import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = FileController.class)
@Slf4j
public class FileControllerAdvise {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRequestDTO> handlerValidationException(MethodArgumentNotValidException e) {
        final var response = ErrorRequestDTO
                .builder()
                .message(e.getMessage())
                .errors(e.getBindingResult().getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage))).build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFileFoundException.class)
    public ResponseEntity<ErrorRequestDTO> handlerNoFileFoundException(NoFileFoundException e) {
        log.info("Error searching file in bucket {} by the reference {} due {}", e.getBucket(), e.getReference(), e.getMessage());
        return new ResponseEntity<>(ErrorRequestDTO.builder()
                .message(e.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
