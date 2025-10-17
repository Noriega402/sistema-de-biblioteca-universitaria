package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.EjemplarService;
import com.umg.catalog_service.web.dto.EjemplarDTO;
import com.umg.catalog_service.web.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ejemplares")
@RequiredArgsConstructor
public class EjemplarController {

    private final EjemplarService service;

    @GetMapping
    public PageResponse<EjemplarDTO> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<EjemplarDTO> p = service.list(page, size);
        return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
    }

    @PostMapping
    public EjemplarDTO create(@RequestBody EjemplarDTO dto) {
        return service.create(dto);
    }
}
