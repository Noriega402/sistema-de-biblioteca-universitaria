package com.umg.catalog_service.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public record BookDTO(
        @NotBlank
        String isbn,
        @NotBlank
        String titulo,
        @NotBlank
        String editorial,
        @Positive
        Short anio,
        @NotNull
        Long categoriaId,

        // Acepta array; si quieres permitir un solo número como array, ver punto 3
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        java.util.Set<Long> autoresIds
        ) {

}
