/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.Region;
import com.umg.catalog_service.web.dto.RegionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionDTO toDto(Region e);
    Region toEntity(RegionDTO d);
}
