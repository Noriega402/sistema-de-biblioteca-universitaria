/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.catalog_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "biblioteca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_biblioteca")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_region", nullable = false)
    private Region region;

    @Column(name = "direccion", length = 200)
    private String direccion;
}
