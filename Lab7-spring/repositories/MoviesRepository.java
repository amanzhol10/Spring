package kz.bitlab.springboot.springbootdemo.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springboot.springbootdemo.entities.Courses;
import kz.bitlab.springboot.springbootdemo.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MoviesRepository extends JpaRepository<Movies,Long> {
}
