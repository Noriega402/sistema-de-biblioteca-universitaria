package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.repository.BibliotecaRepository;
import com.umg.catalog_service.web.dto.BibliotecaDTO;
import com.umg.catalog_service.web.mapper.BibliotecaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final BibliotecaRepository repo;
    private final BibliotecaMapper mapper;

    public Page<BibliotecaDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("nombre"))).map(mapper::toDto);
    }

    public BibliotecaDTO create(BibliotecaDTO d) {
        return mapper.toDto(repo.save(mapper.toEntity(d)));
    }
}
