package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    public boolean existsByCode(String nombre);
}