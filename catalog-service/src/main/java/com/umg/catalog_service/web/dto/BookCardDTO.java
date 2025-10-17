package com.umg.catalog_service.web.dto;

public record BookCardDTO(
        String isbn,
        String titulo,
        String categoria, // nombre
        String editorial,
        Integer anio
        ) {

}
