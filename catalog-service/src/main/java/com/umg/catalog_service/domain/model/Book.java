package com.umg.catalog_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @Column(name = "isbn", length = 20)
    private String isbn;   // PK es VARCHAR(20)
    
    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;
    
    @Column(name = "editorial", length = 120)
    private String editorial;
    
    @Column(name = "anio")
    private Short anio;    // SMALLINT
    
    @ManyToOne
    @JoinColumn(name = "id_categoria") // ← nombre real de la columna FK en la tabla libro
    private Category category;
    
    // Relación M:N con Autor via tabla puente 'libro_autor'
    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id_autor")
    )

    private Set<Author> authors;
}
