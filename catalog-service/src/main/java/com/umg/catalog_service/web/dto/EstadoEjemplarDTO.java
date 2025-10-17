package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.*;

public record EstadoEjemplarDTO(
        Long id,
        @NotBlank
        @Size(max = 60)
        String tipo
        ) {

}
