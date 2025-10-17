package com.umg.catalog_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long id;

    @Column(name = "nombre_autor", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos_autor", nullable = false, length = 120)
    private String apellidos;
}
