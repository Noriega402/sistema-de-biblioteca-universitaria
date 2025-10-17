package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.repository.AuthorRepository;
import com.umg.catalog_service.web.dto.AuthorDTO;
import com.umg.catalog_service.web.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repo;
    private final AuthorMapper mapper;

    public Page<AuthorDTO> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("apellidos").ascending()
                .and(Sort.by("nombres")))).map(mapper::toDto);
    }

    public AuthorDTO create(AuthorDTO d) {
        return mapper.toDto(repo.save(mapper.toEntity(d)));
    }
}
