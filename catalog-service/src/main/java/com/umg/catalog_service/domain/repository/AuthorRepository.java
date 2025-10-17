package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
