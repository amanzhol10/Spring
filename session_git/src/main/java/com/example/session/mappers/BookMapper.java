package com.example.session.mappers;

import com.example.session.dto.BookRequestDTO;
import com.example.session.dto.BookResponseDTO;
import com.example.session.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "category.name", target = "categoryName")
    BookResponseDTO toDto(Book book);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "categoryId", target = "category.id")
    Book toEntity(BookRequestDTO dto);

    List<BookResponseDTO> toDtoList(List<Book> books);
}
