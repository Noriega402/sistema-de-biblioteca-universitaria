//package com.umg.catalog_service.web.advice;
//
//import org.springframework.http.*;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//import java.util.*;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String, Object>> badRequest(IllegalArgumentException ex) {
//        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException ex) {
//        var fields = ex.getBindingResult().getFieldErrors().stream()
//                .map(f -> Map.of("field", f.getField(), "message", f.getDefaultMessage())).toList();
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
//                .body(Map.of("error", "Validación fallida", "fields", fields));
//    }
//}
