package com.umg.catalog_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ejemplar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejemplar")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "isbn", nullable = false)
    private Book libro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_biblioteca", nullable = false)
    private Biblioteca biblioteca;

    @Column(name = "codigo_barra", nullable = false, length = 64, unique = true)
    private String codigoBarra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estado_ejemplar", nullable = false)
    private EstadoEjemplar estado;

    @Column(name = "ubicacion_fisica", length = 60)
    private String ubicacionFisica;
}
