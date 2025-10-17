package com.umg.auth_service.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "nombre_persona", nullable = false, length = 80)
    private String nombre;
    @Column(name = "apellido_persona", nullable = false, length = 80)
    private String apellido;
    @Column(name = "telefono", length = 30)
    private String telefono;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
}
