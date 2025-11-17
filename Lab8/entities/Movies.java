package kz.bitlab.springboot.springbootdemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name = "description",length = 1200)
    private String description;

    @Column(name = "year")
    private int year;

    @Column(name="genre",length = 200)
    private String genre;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actors> actors;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
