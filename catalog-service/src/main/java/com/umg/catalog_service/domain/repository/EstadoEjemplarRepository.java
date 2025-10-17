package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.EstadoEjemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoEjemplarRepository extends JpaRepository<EstadoEjemplar, Long> {
}