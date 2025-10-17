package com.umg.catalog_service.web.dto;

public record AvailableCopyDTO(
        Long ejemplarId,
        String codigoBarra,
        Long bibliotecaId,
        String bibliotecaNombre
        ) {

}
