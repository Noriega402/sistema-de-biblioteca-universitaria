package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.service.CategoryService;
import com.umg.catalog_service.web.dto.CategoryDTO;
import com.umg.catalog_service.web.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/categorias") @RequiredArgsConstructor
public class CategoryController {
  private final CategoryService service;

  @GetMapping
  public PageResponse<CategoryDTO> list(@RequestParam(defaultValue="0") int page,
                                         @RequestParam(defaultValue="10") int size){
    Page<CategoryDTO> p = service.list(page, size);
    return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
  }
  @PostMapping
  public CategoryDTO create(@RequestBody CategoryDTO dto){ return service.create(dto); }
}
