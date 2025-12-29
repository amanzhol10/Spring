package com.example.session.test;

import com.example.session.dto.BookRequestDTO;
import com.example.session.dto.BookResponseDTO;
import com.example.session.entity.Author;
import com.example.session.entity.Book;
import com.example.session.entity.Category;
import com.example.session.mappers.BookMapper;
import com.example.session.repository.AuthorRepository;
import com.example.session.repository.BookRepository;
import com.example.session.repository.CategoryRepository;
import com.example.session.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookMapper bookMapper;

    @Test
    void create_shouldSaveBook() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Test Author");

        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        BookRequestDTO dto = new BookRequestDTO();
        dto.setTitle("Test Book");
        dto.setAuthorId(1L);
        dto.setCategoryId(1L);
        dto.setTotalCopies(5);
        dto.setAvailableCopies(5);

        Book bookEntity = new Book();
        bookEntity.setTitle("Test Book");
        when(bookMapper.toEntity(dto)).thenReturn(bookEntity);
        when(bookMapper.toDto(any(Book.class))).thenAnswer(invocation -> {
            Book b = invocation.getArgument(0);
            BookResponseDTO responseDTO = new BookResponseDTO();
            responseDTO.setTitle(b.getTitle());
            responseDTO.setAuthorName(b.getAuthor().getName());
            responseDTO.setCategoryName(b.getCategory().getName());
            return responseDTO;
        });

        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookResponseDTO createdBook = bookService.create(dto);

        assertThat(createdBook.getTitle()).isEqualTo("Test Book");
        assertThat(createdBook.getAuthorName()).isEqualTo("Test Author");
        assertThat(createdBook.getCategoryName()).isEqualTo("Test Category");

        verify(bookRepository, times(1)).save(any(Book.class));
        verify(authorRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).findById(1L);
    }
}
