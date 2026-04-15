package com.recuperacion.korl.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Intercepts exceptions across the application [cite: 299]
public class GlobalExceptionHandler {

    /**
     * Handles 404 Not Found errors [cite: 301-304].
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        ApiError body = new ApiError("NOT_FOUND", ex.getMessage(), Instant.now(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Handles 400 Bad Request validation errors (Required for Point 4) [cite: 305-318].
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> fields = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fields.put(fe.getField(), fe.getDefaultMessage()); // Capture field-specific errors [cite: 308-310]
        }

        Map<String, Object> body = new HashMap<>();
        body.put("code", "VALIDATION_ERROR");
        body.put("message", "Request validation failed");
        body.put("timestamp", Instant.now());
        body.put("path", req.getRequestURI());
        body.put("fields", fields); // Include the map of failed fields [cite: 311-316]

        return ResponseEntity.badRequest().body(body);
    }
}