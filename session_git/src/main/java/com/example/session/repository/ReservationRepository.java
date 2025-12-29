package com.example.session.repository;


import com.example.session.entity.Reservation;
import com.example.session.entity.User;
import com.example.session.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByBook(Book book);
}

