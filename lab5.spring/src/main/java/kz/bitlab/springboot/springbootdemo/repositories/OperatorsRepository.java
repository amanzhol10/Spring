package kz.bitlab.springboot.springbootdemo.repositories;

import kz.bitlab.springboot.springbootdemo.entities.Operators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorsRepository extends JpaRepository<Operators,Long> {
}
