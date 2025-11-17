package kz.bitlab.springboot.springbootdemo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private int rating;

    private String text;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movies movie;
}
