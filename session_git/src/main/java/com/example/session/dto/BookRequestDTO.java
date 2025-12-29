package com.example.session.dto;


import lombok.Data;

@Data
public class BookRequestDTO {
    private String title;
    private Long authorId;
    private Long categoryId;
    private int totalCopies;
    private int availableCopies;
}
