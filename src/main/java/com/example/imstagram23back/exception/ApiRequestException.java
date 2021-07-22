package com.example.imstagram23back.exception;




public class ApiRequestException extends IllegalArgumentException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
