package com.example.session.mappers;


import com.example.session.dto.ReservationRequestDTO;
import com.example.session.dto.ReservationResponseDTO;
import com.example.session.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "book.title", target = "bookTitle")
    ReservationResponseDTO toDto(Reservation reservation);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Reservation toEntity(ReservationRequestDTO dto);

    List<ReservationResponseDTO> toDtoList(List<Reservation> reservations);
}
