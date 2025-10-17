package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.BibliotecaService;
import com.umg.catalog_service.web.dto.BibliotecaDTO;
import com.umg.catalog_service.web.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bibliotecas")
@RequiredArgsConstructor
public class BibliotecaController {

    private final BibliotecaService service;

    @GetMapping
    public PageResponse<BibliotecaDTO> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BibliotecaDTO> p = service.list(page, size);
        return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
    }

    @PostMapping
    public BibliotecaDTO create(@RequestBody BibliotecaDTO dto) {
        return service.create(dto);
    }
}