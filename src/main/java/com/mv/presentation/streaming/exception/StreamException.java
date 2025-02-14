package com.mv.presentation.streaming.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StreamException extends RuntimeException {

    private final HttpStatus status;

    public StreamException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }

    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(this.status, this.getMessage());
    }
}
