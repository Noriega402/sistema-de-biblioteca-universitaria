package com.umg.catalog_service.service;

import com.umg.catalog_service.domain.model.Ejemplar;
import com.umg.catalog_service.domain.repository.EjemplarRepository;
import com.umg.catalog_service.web.dto.CopyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CopyService {
private final EjemplarRepository repo;


@Transactional(readOnly = true)
public CopyDto getCopy(Long id) {
Ejemplar e = repo.findById(id)
.orElseThrow(() -> new IllegalArgumentException("Ejemplar no encontrado: " + id));
return CopyDto.builder()
.id(e.getId())
.bookIsbn(e.getLibro().getIsbn())
.status(e.getEstado().getTipo()) // o e.getEstadoNombre()
.build();
}


@Transactional
public CopyDto updateStatus(Long id, String newStatus) {
Ejemplar e = repo.findById(id)
.orElseThrow(() -> new IllegalArgumentException("Ejemplar no encontrado: " + id));
e.getEstado().setTipo(newStatus);
Ejemplar saved = repo.save(e);
return CopyDto.builder()
.id(saved.getId())
.bookIsbn(saved.getLibro().getIsbn())
.status(saved.getEstado().getTipo())
.build();
}
}