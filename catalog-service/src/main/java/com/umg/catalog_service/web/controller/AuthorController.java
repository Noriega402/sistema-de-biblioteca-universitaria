package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.AuthorService;
import com.umg.catalog_service.web.dto.AuthorDTO;
import com.umg.catalog_service.web.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/authors") @RequiredArgsConstructor
public class AuthorController {
  private final AuthorService service;

  @GetMapping
  public PageResponse<AuthorDTO> list(@RequestParam(defaultValue="0") int page,
                                     @RequestParam(defaultValue="10") int size){
    Page<AuthorDTO> p = service.list(page, size);
    return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
  }
  @PostMapping
  public AuthorDTO create(@RequestBody AuthorDTO dto){ return service.create(dto); }
}
