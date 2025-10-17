package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
