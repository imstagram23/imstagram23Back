package com.example.imstagram23back.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 07-16 21:36 by 최왕규
 */


@AllArgsConstructor
@Getter
public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;

}
