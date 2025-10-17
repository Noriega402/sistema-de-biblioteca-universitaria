package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.*;

public record CategoryDTO(
        Long id,
        @NotBlank
        @Size(max = 60)
        String nombre
        ) {
}
