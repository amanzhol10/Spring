package com.example.session.mappers;


import com.example.session.dto.CategoryRequestDTO;
import com.example.session.dto.CategoryResponseDTO;
import com.example.session.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toDto(Category category);

    Category toEntity(CategoryRequestDTO dto);

    List<CategoryResponseDTO> toDtoList(List<Category> categories);
}
