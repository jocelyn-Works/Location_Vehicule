package com.example.user.web.controller.Exception;

public class CodeNotValidException extends RuntimeException {
    public CodeNotValidException(String message) {
        super(message);
    }
}
