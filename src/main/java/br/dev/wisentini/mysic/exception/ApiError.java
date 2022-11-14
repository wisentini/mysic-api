package br.dev.wisentini.mysic.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ApiError {
    private final HttpStatus status;
    private final List<Error> errors;

    public ApiError(HttpStatus status, List<Error> errors) {
        this.status = status;
        this.errors = errors;
    }
}
