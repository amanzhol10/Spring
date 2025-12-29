package com.example.session.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservationRequestDTO {
    private Long userId;
    private Long bookId;
    private LocalDate reservationDate;
    private LocalDate returnDate;
}

