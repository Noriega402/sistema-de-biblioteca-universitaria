package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.Author;
import com.umg.catalog_service.web.dto.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(Author e);
    Author toEntity(AuthorDTO d);
}
