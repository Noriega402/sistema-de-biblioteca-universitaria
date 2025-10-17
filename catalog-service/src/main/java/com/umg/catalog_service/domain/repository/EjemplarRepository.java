package com.umg.catalog_service.domain.repository;

import com.umg.catalog_service.domain.model.Ejemplar;
import com.umg.catalog_service.web.dto.BookDetailDTO.LibraryAvailabilityDTO;
import com.umg.catalog_service.web.dto.AvailableCopyDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

    // Disponibilidad agregada por biblioteca (estado = 'DISPONIBLE')
    @Query("""
        select new com.umg.catalog_service.web.dto.LibraryAvailabilityDTO(
          e.biblioteca.id, e.biblioteca.nombre, count(e)
        )
        from Ejemplar e
        where e.libro.isbn = :isbn
          and upper(e.estado.tipo) = 'DISPONIBLE'
        group by e.biblioteca.id, e.biblioteca.nombre
        order by e.biblioteca.nombre asc
        """)
    List<LibraryAvailabilityDTO> availabilityByLibrary(@Param("isbn") String isbn);

    @Query("""
    select count(e) from Ejemplar e
    where e.libro.isbn = :isbn and upper(e.estado.tipo) = 'DISPONIBLE'
    """)
    long totalAvailable(@Param("isbn") String isbn);

    // Listar ejemplares disponibles (con códigos) por biblioteca
    @Query("""
    select new com.umg.catalog_service.web.dto.AvailableCopyDTO(
      e.id, e.codigoBarra, e.biblioteca.id, e.biblioteca.nombre
    )
    from Ejemplar e
    where e.libro.isbn = :isbn and upper(e.estado.tipo) = 'DISPONIBLE'
    order by e.biblioteca.nombre, e.codigoBarra
    """)
    List<AvailableCopyDTO> availableCopies(@Param("isbn") String isbn);
}
