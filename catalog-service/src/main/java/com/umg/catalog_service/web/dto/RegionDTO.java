package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.*;

public record RegionDTO(
        Long id,
        @NotBlank
        @Size(max = 60)
        String nombre
        ) {
}
