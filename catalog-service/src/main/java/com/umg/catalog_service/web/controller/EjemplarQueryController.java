package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.EjemplarQueryService;
import com.umg.catalog_service.web.dto.AvailableCopyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ejemplares")
@RequiredArgsConstructor
public class EjemplarQueryController {

    private final EjemplarQueryService service;

    // Lista códigos de barras disponibles y en qué biblioteca están cada uno de los ejemplares
    @GetMapping("/disponibles")
    public List<AvailableCopyDTO> available(@RequestParam String isbn) {
        return service.availableByIsbn(isbn);
    }
}
