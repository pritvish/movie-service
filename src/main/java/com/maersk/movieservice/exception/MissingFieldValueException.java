package com.maersk.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingFieldValueException extends RuntimeException {

    public MissingFieldValueException(HttpStatus badRequest, String message) {
        super(message);
    }
}
