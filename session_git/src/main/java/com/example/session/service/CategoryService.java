package com.example.session.service;


import com.example.session.dto.CategoryRequestDTO;
import com.example.session.dto.CategoryResponseDTO;
import com.example.session.entity.Category;
import com.example.session.mappers.CategoryMapper;
import com.example.session.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public List<CategoryResponseDTO> getAll() {
        return categoryMapper.toDtoList(categoryRepository.findAll());
    }

    public CategoryResponseDTO getById(Long id) {
        return categoryMapper.toDto(
                categoryRepository.findById(id).orElseThrow()
        );
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
