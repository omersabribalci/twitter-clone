package com.wtech.twitter.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TwitterException.class)
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                twitterException.getHttpStatus().value(),
                twitterException.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(twitterErrorResponse, twitterException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {

        log.error("Unexpected error: ", exception);

        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
