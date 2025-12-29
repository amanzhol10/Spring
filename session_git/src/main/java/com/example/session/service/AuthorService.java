package com.example.session.service;


import com.example.session.dto.AuthorRequestDTO;
import com.example.session.dto.AuthorResponseDTO;
import com.example.session.entity.Author;
import com.example.session.mappers.AuthorMapper;
import com.example.session.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDTO create(AuthorRequestDTO dto) {
        Author author = authorMapper.toEntity(dto);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public List<AuthorResponseDTO> getAll() {
        return authorMapper.toDtoList(authorRepository.findAll());
    }

    public AuthorResponseDTO getById(Long id) {
        return authorMapper.toDto(
                authorRepository.findById(id).orElseThrow()
        );
    }

    public AuthorResponseDTO update(Long id, AuthorRequestDTO dto) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setName(dto.getName());
        author.setBiography(dto.getBiography());
        return authorMapper.toDto(authorRepository.save(author));
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
