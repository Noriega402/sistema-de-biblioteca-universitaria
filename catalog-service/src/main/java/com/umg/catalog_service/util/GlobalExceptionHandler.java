package com.umg.catalog_service.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String,Object>> build(HttpStatus status, String message, String path, Map<String,Object> details) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("success", false);
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);
        body.put("details", details);
        body.put("timestamp", Instant.now());
        return ResponseEntity.status(status).body(body);
    }

    private static String rootMessage(Throwable ex) {
        Throwable t = ex;
        while (t.getCause() != null) t = t.getCause();
        return t.getMessage() != null ? t.getMessage() : ex.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(NotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String,Object> details = new LinkedHashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            details.put(fe.getField(), fe.getDefaultMessage());
        }
        return build(HttpStatus.BAD_REQUEST, "Validation failed", req.getRequestURI(), details);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        Map<String,Object> details = Map.of("hint", "Verifica JSON válido, tipos y nombres de campos");
        return build(HttpStatus.BAD_REQUEST, rootMessage(ex), req.getRequestURI(), details);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,Object>> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String,Object> details = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(cv -> details.put(cv.getPropertyPath().toString(), cv.getMessage()));
        return build(HttpStatus.BAD_REQUEST, "Constraint violation", req.getRequestURI(), details);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,Object>> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        String message = rootMessage(ex);
        Map<String,Object> details = new LinkedHashMap<>();
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof SQLException sql) {
            details.put("sqlState", sql.getSQLState());
            details.put("errorCode", sql.getErrorCode());
        }
        details.put("hint", "Revisa FKs NOT NULL y mapeos DTO -> Entidad (category/authors).");
        return build(HttpStatus.CONFLICT, message, req.getRequestURI(), details);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleAny(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, rootMessage(ex), req.getRequestURI(), null);
    }
}
