package com.humber.ImageService.exception;

import com.humber.ImageService.common.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnableToSaveImageExceptionHandler {
    @ExceptionHandler(UnableToSaveImageException.class)
    public ResponseEntity<CustomErrorResponse> unableToSaveImageExceptionHandler(UnableToSaveImageException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        CustomErrorResponse
                                .builder()
                                .message(exc.getMessage())
                                .timestamp(System.currentTimeMillis())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }
}
