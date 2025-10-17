package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.repository.CategoryRepository;
import com.umg.catalog_service.web.dto.CategoryDTO;
import com.umg.catalog_service.web.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    public Page<CategoryDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("nombre"))).map(mapper::toDto);
    }

    public CategoryDTO create(CategoryDTO d) {
        return mapper.toDto(repo.save(mapper.toEntity(d)));
    }
}
