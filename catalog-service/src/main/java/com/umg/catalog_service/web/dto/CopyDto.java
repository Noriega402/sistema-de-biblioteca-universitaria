package com.umg.catalog_service.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CopyDto {

    private Long id; // id del ejemplar
    private String bookIsbn; // ISBN del libro
    private String status; // DISPONIBLE | RESERVADO | NO_DISPONIBLE | DAÑADO | PERDIDO
}
