package com.mv.presentation.streaming.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StreamExceptionHandler {

    @ExceptionHandler(StreamException.class)
    public ResponseEntity<ErrorResponse> handleMedException(final StreamException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.toErrorResponse());
    }
}
