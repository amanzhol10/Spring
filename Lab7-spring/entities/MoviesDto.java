package kz.bitlab.springboot.springbootdemo.entities;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MoviesDto {
    private Long id;

    private String title;

    private String description;

    private int year;

    private String genre;
}
