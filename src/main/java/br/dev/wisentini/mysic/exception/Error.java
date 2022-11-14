package br.dev.wisentini.mysic.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;

@Getter
@JsonPropertyOrder({"fieldName", "message"})
public class Error {
    @JsonProperty("field")
    private final String fieldName;
    private final String message;

    public Error(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public Error(ConstraintViolation<?> constraintViolation) {
        this(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
    }

    public Error(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
