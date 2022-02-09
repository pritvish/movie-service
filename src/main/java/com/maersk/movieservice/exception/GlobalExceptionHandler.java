package com.maersk.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MissingFieldValueException.class)
    public ResponseEntity<Object> handleMissingFieldValueException(MissingFieldValueException missingFieldValueException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timetamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("message", missingFieldValueException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FutureDateException.class)
    public ResponseEntity<Object> handleMissingFieldValueException(FutureDateException futureDateException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timetamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("message", futureDateException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException idNotFoundException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timetamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("message", idNotFoundException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyMovieDataBaseException.class)
    public ResponseEntity<Object> handleEmptyMovieDataBaseException(EmptyMovieDataBaseException emptyMovieDataBaseException, WebRequest webRequest) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timetamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("message", emptyMovieDataBaseException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
