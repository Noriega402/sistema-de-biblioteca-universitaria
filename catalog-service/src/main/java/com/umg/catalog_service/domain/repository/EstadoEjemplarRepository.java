package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.EstadoEjemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EstadoEjemplarRepository extends JpaRepository<EstadoEjemplar, Long> {
    Optional<EstadoEjemplar> findByNombreIgnoreCase(String nombre);
    // (opcional) Optional<EstadoEjemplar> findByCodigo(String codigo);
}