package br.dev.wisentini.mysic.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintValidation(ConstraintViolationException ex) {
        return new ResponseEntity<>(
            new ApiError(
                HttpStatus.BAD_REQUEST,
                ex
                .getConstraintViolations()
                .stream()
                .map(Error::new)
                .collect(Collectors.toList())
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(
            new ApiError(
                HttpStatus.BAD_REQUEST,
                ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(Error::new)
                .collect(Collectors.toList())
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBindException(BindException ex) {
        return new ResponseEntity<>(
            new ApiError(
                HttpStatus.BAD_REQUEST,
                ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(Error::new)
                .collect(Collectors.toList())
            ),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConversionFailed(ConversionFailedException ex) {
        return new ResponseEntity<>(
            new ApiError(
                HttpStatus.BAD_REQUEST,
                List.of(new Error(Objects.requireNonNull(ex.getValue()).toString(), ex.getCause().getMessage()))
            ),
            HttpStatus.BAD_REQUEST
        );
    }
}
