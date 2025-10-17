package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.*;

public record EjemplarDTO(
        Long id,
        @NotBlank
        @Size(max = 64)
        String codigoBarra,
        @NotBlank
        @Size(max = 20)
        String isbn, // FK a libro
        @NotNull
        Long bibliotecaId,
        @NotNull
        Long estadoId,
        @Size(max = 60)
        String ubicacionFisica
        ) {

}
