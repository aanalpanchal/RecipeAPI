package com.humber.CommentService.exception;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException() {
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
