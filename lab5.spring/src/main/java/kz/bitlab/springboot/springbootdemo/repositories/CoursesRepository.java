package kz.bitlab.springboot.springbootdemo.repositories;

import jakarta.transaction.Transactional;
import kz.bitlab.springboot.springbootdemo.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CoursesRepository extends JpaRepository<Courses,Long> {
}
