package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
}