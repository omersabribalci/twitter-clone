package com.wtech.twitter.exceptions;

import com.wtech.twitter.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TwitterException.class)
    public ResponseEntity<ApiResponse<?>> handleException(TwitterException twitterException) {
        return new ResponseEntity<>(
                ApiResponse.error(twitterException.getMessage(), twitterException.getHttpStatus().value()),
                twitterException.getHttpStatus()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleException(DataIntegrityViolationException exception) {
        log.error("Username or email already exists",exception);

        return new ResponseEntity<>(
                ApiResponse.error("Username or email already exists", HttpStatus.CONFLICT.value()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception exception) {

        log.error("Unexpected error: ", exception);

        return new ResponseEntity<>(
                ApiResponse.error("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }



}
