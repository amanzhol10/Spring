package kz.bitlab.springboot.springbootdemo.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private String author;
    private int rating;
    private String text;
}
