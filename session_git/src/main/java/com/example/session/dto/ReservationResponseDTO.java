package com.example.session.dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservationResponseDTO {
    private Long id;
    private String username;
    private String bookTitle;
    private LocalDate reservationDate;
    private LocalDate returnDate;
}
