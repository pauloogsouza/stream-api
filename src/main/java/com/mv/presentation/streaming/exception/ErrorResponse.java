package com.mv.presentation.streaming.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private final HttpStatus status;

    private final String message;

    private final String code;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.code = null;
    }

    public ErrorResponse(StreamException ex) {
        this.status = ex.getStatus();
        this.message = ex.getMessage();
        this.code = null;
    }
}
