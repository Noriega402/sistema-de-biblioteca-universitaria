package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.model.Ejemplar;
import com.umg.catalog_service.domain.repository.EjemplarRepository;
import com.umg.catalog_service.web.dto.EjemplarDTO;
import com.umg.catalog_service.web.mapper.EjemplarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EjemplarService {

    private final EjemplarRepository repo;
    private final EjemplarMapper mapper;

    public Page<EjemplarDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("id"))).map(mapper::toDto);
    }

    public EjemplarDTO create(EjemplarDTO d) {
        Ejemplar e = mapper.toEntity(d);
        return mapper.toDto(repo.save(e));
    }
}
