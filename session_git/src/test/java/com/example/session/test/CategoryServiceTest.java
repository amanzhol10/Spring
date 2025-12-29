package com.example.session.test;


import com.example.session.dto.CategoryRequestDTO;
import com.example.session.dto.CategoryResponseDTO;
import com.example.session.entity.Category;
import com.example.session.mappers.CategoryMapper;
import com.example.session.repository.CategoryRepository;
import com.example.session.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void create_shouldSaveCategory() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("Fantasy");
        dto.setDescription("Fantasy books");

        Category category = new Category();
        Category savedCategory = new Category();
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();

        when(categoryMapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(savedCategory);
        when(categoryMapper.toDto(savedCategory)).thenReturn(responseDTO);

        CategoryResponseDTO result = categoryService.create(dto);

        assertNotNull(result);
        verify(categoryRepository).save(category);
    }

    @Test
    void getAll_shouldReturnList() {
        Category category = new Category();
        CategoryResponseDTO dto = new CategoryResponseDTO();

        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryMapper.toDtoList(List.of(category))).thenReturn(List.of(dto));

        List<CategoryResponseDTO> result = categoryService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void getById_shouldReturnCategory() {
        Category category = new Category();
        CategoryResponseDTO dto = new CategoryResponseDTO();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(dto);

        CategoryResponseDTO result = categoryService.getById(1L);

        assertNotNull(result);
    }

    @Test
    void update_shouldUpdateCategory() {
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("Updated");
        dto.setDescription("Updated desc");

        Category category = new Category();
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(responseDTO);

        CategoryResponseDTO result = categoryService.update(1L, dto);

        assertNotNull(result);
        assertEquals("Updated", category.getName());
    }

    @Test
    void delete_shouldCallRepository() {
        categoryService.delete(1L);
        verify(categoryRepository).deleteById(1L);
    }
}
