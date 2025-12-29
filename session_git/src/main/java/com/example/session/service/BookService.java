package com.example.session.service;


import com.example.session.dto.BookRequestDTO;
import com.example.session.dto.BookResponseDTO;
import com.example.session.entity.Author;
import com.example.session.entity.Book;
import com.example.session.entity.Category;
import com.example.session.mappers.BookMapper;
import com.example.session.repository.AuthorRepository;
import com.example.session.repository.BookRepository;
import com.example.session.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookResponseDTO create(BookRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow();
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow();

        Book book = bookMapper.toEntity(dto);
        book.setAuthor(author);
        book.setCategory(category);

        return bookMapper.toDto(bookRepository.save(book));
    }

    public List<BookResponseDTO> getAll() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    public BookResponseDTO getById(Long id) {
        return bookMapper.toDto(
                bookRepository.findById(id).orElseThrow()
        );
    }

    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setTitle(dto.getTitle());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        return bookMapper.toDto(bookRepository.save(book));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
