package com.maersk.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyMovieDataBaseException extends RuntimeException {

    public EmptyMovieDataBaseException(HttpStatus badRequest, String message) {
        super(message);
    }
}
