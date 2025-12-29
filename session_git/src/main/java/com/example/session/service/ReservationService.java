package com.example.session.service;


import com.example.session.dto.ReservationRequestDTO;
import com.example.session.dto.ReservationResponseDTO;
import com.example.session.entity.Book;
import com.example.session.entity.Reservation;
import com.example.session.entity.User;
import com.example.session.mappers.ReservationMapper;
import com.example.session.repository.BookRepository;
import com.example.session.repository.ReservationRepository;
import com.example.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReservationMapper reservationMapper;

    public ReservationResponseDTO create(ReservationRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        Book book = bookRepository.findById(dto.getBookId()).orElseThrow();

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No available copies");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        Reservation reservation = reservationMapper.toEntity(dto);
        reservation.setUser(user);
        reservation.setBook(book);

        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    public List<ReservationResponseDTO> getAll() {
        return reservationMapper.toDtoList(reservationRepository.findAll());
    }

    public ReservationResponseDTO getById(Long id) {
        return reservationMapper.toDto(
                reservationRepository.findById(id).orElseThrow()
        );
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean isOwnerOrLibrarian(Long reservationId, Long userId) {
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return reservation.getUser().getId().equals(userId) ||
                userRepository.findById(userId)
                        .map(user -> user.getPermissions().stream()
                                .anyMatch(p -> p.getName().equals("ROLE_LIBRARIAN")))
                        .orElse(false);
    }

}
