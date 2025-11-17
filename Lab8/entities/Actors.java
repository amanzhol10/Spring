package kz.bitlab.springboot.springbootdemo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actors")
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    @ManyToMany(mappedBy = "actors")
    private List<Movies> movies;
}
