package kz.bitlab.springboot.springbootdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="movies")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "title",length = 200)
    private String title;

    @Column(name = "description",length = 200)
    private String description;

    @Column(name = "year")
    private int year;

    @Column(name="genre",length = 200)
    private String genre;
}
