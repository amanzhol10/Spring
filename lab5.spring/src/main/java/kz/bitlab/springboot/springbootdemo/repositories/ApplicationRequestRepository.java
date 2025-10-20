package kz.bitlab.springboot.springbootdemo.repositories;

import kz.bitlab.springboot.springbootdemo.entities.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
}
