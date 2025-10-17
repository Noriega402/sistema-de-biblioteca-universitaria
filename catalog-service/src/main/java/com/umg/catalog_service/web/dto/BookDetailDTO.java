package com.umg.catalog_service.web.dto;

import java.util.List;

public record BookDetailDTO(
            String isbn,
            String titulo,
            String editorial,
            Integer anio,
            String categoria, // nombre
            List<String> autores, // "Apellido, Nombre"
            AvailabilityDTO disponibilidad
        ) {

    public record AvailabilityDTO(
            long totalDisponibles,
            List<LibraryAvailabilityDTO> porBiblioteca
            ) {

    }

    public record LibraryAvailabilityDTO(
            Long bibliotecaId,
            String bibliotecaNombre,
            long disponibles
            ) {

    }
}
