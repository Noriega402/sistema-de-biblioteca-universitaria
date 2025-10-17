package com.umg.catalog_service.web.controller;

import com.umg.catalog_service.domain.search.SearchFilter;
import com.umg.catalog_service.service.BookQueryService;
import com.umg.catalog_service.web.dto.PageResponse;
import com.umg.catalog_service.web.dto.SearchResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class BookQueryController {

  private final BookQueryService service;

  @GetMapping("/_search")
  public PageResponse<SearchResultDTO> search(@RequestParam("q") String q,
                                              @RequestParam(value = "filter", required = false) String filter,
                                              @RequestParam(defaultValue="0") int page,
                                              @RequestParam(defaultValue="10") int size) {
    SearchFilter f = null;
    if (filter != null && !filter.isBlank()) {
      try {
        f = SearchFilter.valueOf(filter.trim().toUpperCase()); // TITULO|AUTOR|CATEGORIA|ISBN
      } catch (IllegalArgumentException ignored) {
        // si viene algo fuera de catálogo, se ignora y hace búsqueda general
      }
    }
    Page<SearchResultDTO> p = service.search(q, f, page, size);
    return new PageResponse<>(p.getContent(), p.getTotalElements(), p.getTotalPages(), page, size);
  }
}
