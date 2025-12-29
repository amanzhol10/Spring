package com.example.session.controller;

import com.example.session.dto.AuthorRequestDTO;
import com.example.session.dto.AuthorResponseDTO;
import com.example.session.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public AuthorResponseDTO create(@RequestBody AuthorRequestDTO dto) {
        return authorService.create(dto);
    }

    @GetMapping
    public List<AuthorResponseDTO> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponseDTO getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public AuthorResponseDTO update(@PathVariable Long id,
                                    @RequestBody AuthorRequestDTO dto) {
        return authorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIBRARIAN')")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
