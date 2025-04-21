package com.reservation.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.reservation.response.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<Response<String>> handleBusinessException(Exception ex) {
        return buildError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<String>> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + Objects.requireNonNull(e.getDefaultMessage()))
                .collect(Collectors.joining("; "));
        return buildError(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Response<String>> handleInvalidEnumValue(InvalidFormatException ex) {
        String message = "Invalid value provided";
        if (ex.getTargetType().isEnum() && !ex.getPath().isEmpty()) {
            String fieldName = ex.getPath().get(0).getFieldName();
            message = String.format("Invalid value '%s' for field '%s'", ex.getValue(), fieldName);
        }
        return buildError(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response<String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildError("Duplicate entry error", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<String>> handleOtherExceptions(Exception ex) {
        System.out.println(ex.getMessage());
        return buildError("Unexpected server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Response<String>> buildError(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(Response.error(message, status));
    }
}
