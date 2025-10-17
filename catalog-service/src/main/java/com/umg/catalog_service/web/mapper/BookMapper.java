// src/main/java/com/umg/catalog_service/web/mapper/BookMapper.java
package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.Book;
import com.umg.catalog_service.web.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Book -> DTO (exporta los IDs)
    @Mapping(target = "categoriaId",
            expression = "java(book.getCategory() != null ? book.getCategory().getId() : null)")
    @Mapping(target = "autoresIds",
            expression = "java(book.getAuthors() != null ? book.getAuthors().stream().map(a -> a.getId()).collect(java.util.stream.Collectors.toSet()) : java.util.Collections.emptySet())")
    BookDTO toDto(Book book);

    // DTO -> Book (relaciones las pondremos en el Service)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Book toEntity(BookDTO dto);
}
