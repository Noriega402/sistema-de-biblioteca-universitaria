package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.*;
import com.umg.catalog_service.domain.repository.*;
import com.umg.catalog_service.web.dto.EjemplarDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EjemplarMapper {

    @Autowired
    protected BookRepository libroRepo;
    @Autowired
    protected BibliotecaRepository bibliotecaRepo;
    @Autowired
    protected EstadoEjemplarRepository estadoRepo;

    @Mapping(target = "isbn", source = "libro.isbn")
    @Mapping(target = "bibliotecaId", source = "biblioteca.id")
    @Mapping(target = "estadoId", source = "estado.id")
    public abstract EjemplarDTO toDto(Ejemplar e);

    @Mapping(target = "libro", ignore = true)
    @Mapping(target = "biblioteca", ignore = true)
    @Mapping(target = "estado", ignore = true)
    public abstract Ejemplar toEntity(EjemplarDTO d);

    @AfterMapping
    protected void link(EjemplarDTO d, @MappingTarget Ejemplar e) {
        if (d.isbn() != null) {
            e.setLibro(libroRepo.findById(d.isbn())
                    .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + d.isbn())));
        }
        if (d.bibliotecaId() != null) {
            e.setBiblioteca(bibliotecaRepo.findById(d.bibliotecaId())
                    .orElseThrow(() -> new IllegalArgumentException("Biblioteca no encontrada: " + d.bibliotecaId())));
        }
        if (d.estadoId() != null) {
            e.setEstado(estadoRepo.findById(d.estadoId())
                    .orElseThrow(() -> new IllegalArgumentException("Estado ejemplar no encontrado: " + d.estadoId())));
        }
    }
}
