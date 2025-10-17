// web/dto/SearchResultDTO.java  (búsqueda unificada)
package com.umg.catalog_service.web.dto;

public record SearchResultDTO(
        String tipo, // "LIBRO" | "AUTOR" | "CATEGORIA"
        String id, // isbn o id numérico como String
        String titulo, // título de libro o nombre mostrado
        String extra // autor(es), categoría, etc.
        ) {

}
