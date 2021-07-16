package com.example.imstagram23back.exception;


/**
 * 07-16 21:36 by 최왕규
 */

public class ApiRequestException extends IllegalArgumentException {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
