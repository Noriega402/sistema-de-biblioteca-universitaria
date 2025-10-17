package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.*;

public record AuthorDTO(
        Long id,
        @NotBlank
        @Size(max = 100)
        String nombres,
        @NotBlank
        @Size(max = 120)
        String apellidos
        ) {

}
