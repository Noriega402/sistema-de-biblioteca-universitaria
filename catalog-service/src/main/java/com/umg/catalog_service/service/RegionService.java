package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.repository.RegionRepository;
import com.umg.catalog_service.web.dto.RegionDTO;
import com.umg.catalog_service.web.mapper.RegionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository repo;
    private final RegionMapper mapper;

    public Page<RegionDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("nombre"))).map(mapper::toDto);
    }

    public RegionDTO create(RegionDTO d) {
        return mapper.toDto(repo.save(mapper.toEntity(d)));
    }
}
