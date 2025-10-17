package com.umg.catalog_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_ejemplar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoEjemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_ejemplar")
    private Long id;

    @Column(name = "tipo_estado_ejemplar", nullable = false, length = 60, unique = true)
    private String tipo;   // 'DISPONIBLE', 'RESERVADO', ...
}
