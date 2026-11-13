package com.example.noteapi.exception;

public class LoginArgumentNotValidationException extends RuntimeException {

    public LoginArgumentNotValidationException(String message) {
        super(message);
    }
}