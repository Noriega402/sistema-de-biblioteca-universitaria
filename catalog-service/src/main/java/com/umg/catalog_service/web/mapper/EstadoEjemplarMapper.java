package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.EstadoEjemplar;
import com.umg.catalog_service.web.dto.EstadoEjemplarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoEjemplarMapper {
    EstadoEjemplarDTO toDto(EstadoEjemplar e);
    EstadoEjemplar toEntity(EstadoEjemplarDTO d);
}
