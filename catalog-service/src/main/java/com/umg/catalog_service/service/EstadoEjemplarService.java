package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.repository.EstadoEjemplarRepository;
import com.umg.catalog_service.web.dto.EstadoEjemplarDTO;
import com.umg.catalog_service.web.mapper.EstadoEjemplarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoEjemplarService {

    private final EstadoEjemplarRepository repo;
    private final EstadoEjemplarMapper mapper;

    public Page<EstadoEjemplarDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("tipo"))).map(mapper::toDto);
    }

    public EstadoEjemplarDTO create(EstadoEjemplarDTO d) {
        return mapper.toDto(repo.save(mapper.toEntity(d)));
    }
}
