package com.example.session.mappers;


import com.example.session.dto.AuthorRequestDTO;
import com.example.session.dto.AuthorResponseDTO;
import com.example.session.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDTO toDto(Author author);

    Author toEntity(AuthorRequestDTO dto);

    List<AuthorResponseDTO> toDtoList(List<Author> authors);
}
