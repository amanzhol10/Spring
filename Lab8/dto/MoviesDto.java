package kz.bitlab.springboot.springbootdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MoviesDto {
    private Long id;
    private String title;
    private String description;
    private int year;
    private String genre;
    private List<ActorDto> actors;
    private List<ReviewDto> reviews;
}

