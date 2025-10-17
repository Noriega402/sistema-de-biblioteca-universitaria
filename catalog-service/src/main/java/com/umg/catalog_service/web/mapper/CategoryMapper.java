package com.umg.catalog_service.web.mapper;

import com.umg.catalog_service.domain.model.Category;
import com.umg.catalog_service.web.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDto(Category e);
    Category toEntity(CategoryDTO d);
}
