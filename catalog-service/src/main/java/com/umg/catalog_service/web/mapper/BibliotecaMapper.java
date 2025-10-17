package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.*;
import com.umg.catalog_service.domain.repository.RegionRepository;
import com.umg.catalog_service.web.dto.BibliotecaDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BibliotecaMapper {

  @Autowired protected RegionRepository regionRepo;

  @Mapping(target="regionId", source="region.id")
  public abstract BibliotecaDTO toDto(Biblioteca e);

  @Mapping(target="region", ignore=true)
  public abstract Biblioteca toEntity(BibliotecaDTO d);

  @AfterMapping
  protected void link(BibliotecaDTO d, @MappingTarget Biblioteca e){
    if (d.regionId()!=null) {
      e.setRegion(regionRepo.findById(d.regionId())
        .orElseThrow(() -> new IllegalArgumentException("Región no encontrada: "+d.regionId())));
    }
  }
}