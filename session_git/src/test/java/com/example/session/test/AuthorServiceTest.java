package com.example.session.test;


import com.example.session.dto.AuthorRequestDTO;
import com.example.session.dto.AuthorResponseDTO;
import com.example.session.entity.Author;
import com.example.session.mappers.AuthorMapper;
import com.example.session.repository.AuthorRepository;
import com.example.session.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void create_shouldSaveAuthor() {
        AuthorRequestDTO dto = new AuthorRequestDTO();
        Author author = new Author();

        when(authorMapper.toEntity(dto)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(new AuthorResponseDTO());

        AuthorResponseDTO result = authorService.create(dto);

        assertThat(result).isNotNull();
        verify(authorRepository).save(author);
    }

    @Test
    void getAll_shouldReturnAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(new Author()));

        List<AuthorResponseDTO> result = authorService.getAll();

        assertThat(result).isNotNull();
    }
}
