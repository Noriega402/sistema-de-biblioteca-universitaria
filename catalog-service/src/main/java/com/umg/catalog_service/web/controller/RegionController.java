package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.RegionService;
import com.umg.catalog_service.web.dto.RegionDTO;
import com.umg.catalog_service.web.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regiones")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService service;

    @GetMapping
    public PageResponse<RegionDTO> list(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<RegionDTO> p = service.list(page, size);
        return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
    }

    @PostMapping
    public RegionDTO create(@RequestBody RegionDTO dto) {
        return service.create(dto);
    }
}
