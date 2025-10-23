package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.BookService;
import com.umg.catalog_service.web.dto.BookDTO;
import com.umg.catalog_service.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libros")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookDTO>> create(@Valid @RequestBody BookDTO dto) {
        BookDTO saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Libro creado correctamente", saved));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<ApiResponse<BookDTO>> update(@PathVariable String isbn, @Valid @RequestBody BookDTO dto) {
        BookDTO updated = service.update(isbn, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Libro actualizado correctamente", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String isbn) {
        service.delete(isbn);
        return ResponseEntity.ok(new ApiResponse<>(true, "Libro eliminado correctamente", null));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ApiResponse<BookDTO>> findById(@PathVariable String isbn) {
        BookDTO dto = service.findById(isbn);
        return ResponseEntity.ok(new ApiResponse<>(true, "Libro encontrado correctamente", dto));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<BookDTO>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<BookDTO> result = service.list(page, size);
        return ResponseEntity.ok(new ApiResponse<>(true, "Listado de libros", result));
    }
}
