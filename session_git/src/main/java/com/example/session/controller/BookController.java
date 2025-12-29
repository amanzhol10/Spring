package com.example.session.controller;

import com.example.session.dto.BookRequestDTO;
import com.example.session.dto.BookResponseDTO;
import com.example.session.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public BookResponseDTO create(@RequestBody BookRequestDTO dto) {
        return bookService.create(dto);
    }

    @GetMapping
    public List<BookResponseDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public BookResponseDTO update(@PathVariable Long id, @RequestBody BookRequestDTO dto) {
        return bookService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
