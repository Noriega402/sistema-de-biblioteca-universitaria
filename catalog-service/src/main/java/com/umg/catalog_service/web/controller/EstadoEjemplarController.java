package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.EstadoEjemplarService;
import com.umg.catalog_service.web.dto.EstadoEjemplarDTO;
import com.umg.catalog_service.web.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/estados-ejemplares") @RequiredArgsConstructor
public class EstadoEjemplarController {
  private final EstadoEjemplarService service;

  @GetMapping
  public PageResponse<EstadoEjemplarDTO> list(@RequestParam(defaultValue="0") int page,
                                              @RequestParam(defaultValue="10") int size){
    Page<EstadoEjemplarDTO> p = service.list(page, size);
    return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
  }
  @PostMapping
  public EstadoEjemplarDTO create(@RequestBody EstadoEjemplarDTO dto){ return service.create(dto); }
}
