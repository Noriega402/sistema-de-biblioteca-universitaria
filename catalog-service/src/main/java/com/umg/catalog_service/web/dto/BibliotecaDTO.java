package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.*;

public record BibliotecaDTO(
        Long id,
        @NotBlank
        @Size(max = 100)
        String nombre,
        @NotNull
        Long regionId,
        @Size(max = 200)
        String direccion
        ) {

}
