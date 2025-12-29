package com.example.session.dto;


import lombok.Data;

@Data
public class BookResponseDTO {
    private Long id;
    private String title;
    private String authorName;
    private String categoryName;
    private int totalCopies;
    private int availableCopies;
}
