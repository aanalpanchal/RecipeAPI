package com.humber.ImageService.exception;

public class UnableToSaveImageException extends RuntimeException {
    public UnableToSaveImageException() {
    }

    public UnableToSaveImageException(String message) {
        super(message);
    }
}
